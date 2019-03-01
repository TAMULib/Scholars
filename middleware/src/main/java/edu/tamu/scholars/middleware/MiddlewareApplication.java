package edu.tamu.scholars.middleware;

import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_DURATION_IN_DAYS;
import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_MAX_LENGTH;
import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_MIN_LENGTH;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.JsonSchemaFactory;
import com.fasterxml.jackson.module.jsonSchema.factories.ObjectVisitor;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.VisitorContext;
import com.fasterxml.jackson.module.jsonSchema.factories.WrapperFactory;
import com.fasterxml.jackson.module.jsonSchema.types.AnySchema;
import com.fasterxml.jackson.module.jsonSchema.types.ArraySchema;
import com.fasterxml.jackson.module.jsonSchema.types.ObjectSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;
import com.sun.codemodel.JCodeModel;

import edu.tamu.scholars.middleware.auth.config.AuthConfig;
import edu.tamu.scholars.middleware.auth.config.PasswordConfig;
import edu.tamu.scholars.middleware.config.MiddlewareConfig;
import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@EnableScheduling
@SpringBootApplication
public class MiddlewareApplication {

    @Autowired
    private MiddlewareConfig middlewareConfig;

    @Value("classpath:/")
    public Resource classpath;

    public static void main(String[] args) {
        SpringApplication.run(MiddlewareApplication.class, args);
    }

    @PostConstruct
    private void initializePropertyConstants() {
        AuthConfig auth = middlewareConfig.getAuth();
        PasswordConfig password = auth.getPassword();
        PASSWORD_DURATION_IN_DAYS = password.getDuration();
        PASSWORD_MIN_LENGTH = password.getMinLength();
        PASSWORD_MAX_LENGTH = password.getMaxLength();
    }

    @PostConstruct
    private void generateSchema() throws ClassNotFoundException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());

        File schemaDirectory = new File(String.format("%s/schema", classpath.getFile().getAbsolutePath()));

        if (!schemaDirectory.exists()) {
            schemaDirectory.mkdir();
        }

        JCodeModel codeModel = new JCodeModel();

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() {
                return true;
            }
        };

        SolrDocumentSchemaFactoryWrapper solrDocumentVisitor = new SolrDocumentSchemaFactoryWrapper();

        SchemaMapper schemaMapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);

        provider.addIncludeFilter(new AnnotationTypeFilter(SolrDocument.class));

        for (BeanDefinition beanDefinition : provider.findCandidateComponents("edu.tamu.scholars.middleware.discovery.model")) {
            Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());

            objectMapper.acceptJsonFormatVisitor(clazz, solrDocumentVisitor);

            JsonSchema jsonSchema = solrDocumentVisitor.finalSchema();

            File schema = new File(String.format("%s/%s.json", schemaDirectory.getAbsolutePath(), clazz.getSimpleName()));

            objectWriter.writeValue(schema, jsonSchema);

            schemaMapper.generate(codeModel, clazz.getSimpleName(), "edu.tamu.scholars.middleware.discovery.model", schema.toURI().toURL());
        }

        File sourceDirectory = new File(String.format("%s/source", classpath.getFile().getAbsolutePath()));

        if (!sourceDirectory.exists()) {
            sourceDirectory.mkdir();
        }

        codeModel.build(sourceDirectory);

    }

    public static class SolrDocumentSchemaFactoryWrapper extends SchemaFactoryWrapper {

        private final ObjectMapper mapper = new ObjectMapper();

        private static class SolrDocumentSchemaFactoryWrapperFactory extends WrapperFactory {
            @Override
            public SchemaFactoryWrapper getWrapper(SerializerProvider p) {
                SchemaFactoryWrapper wrapper = new SolrDocumentSchemaFactoryWrapper();
                wrapper.setProvider(p);
                return wrapper;
            };

            @Override
            public SchemaFactoryWrapper getWrapper(SerializerProvider p, VisitorContext rvc) {
                SchemaFactoryWrapper wrapper = new SolrDocumentSchemaFactoryWrapper();
                wrapper.setProvider(p);
                wrapper.setVisitorContext(rvc);
                return wrapper;
            }
        };

        public SolrDocumentSchemaFactoryWrapper() {
            super(new SolrDocumentSchemaFactoryWrapperFactory());
            schemaProvider = new SolrDocumentJsonSchemaFactory();
        }

        @Override
        public JsonObjectFormatVisitor expectObjectFormat(JavaType convertedType) {
            ObjectVisitor visitor = (ObjectVisitor) super.expectObjectFormat(convertedType);
            SolrDocumentObjectSchema solrDocumentObjectSchema = new SolrDocumentObjectSchema(convertedType);
            ObjectSchema schema = visitor.getSchema();
            schema.setAdditionalProperties(new ObjectSchema.SchemaAdditionalProperties(solrDocumentObjectSchema));
            return visitor;
        }

        public class SolrDocumentJsonSchemaFactory extends JsonSchemaFactory {

            @Override
            public StringSchema stringSchema() {
                return new StringSchema() {
                    private ObjectNode indexed;
                    private ObjectNode propertySource;

                    @Override
                    public void enrichWithBeanProperty(BeanProperty beanProperty) {
                        super.enrichWithBeanProperty(beanProperty);
                        Indexed indexed = beanProperty.getAnnotation(Indexed.class);
                        if (indexed != null) {
                            setIndexed(toObjectNode(indexed));
                        }
                        PropertySource propertySource = beanProperty.getAnnotation(PropertySource.class);
                        if (propertySource != null) {
                            setPropertySource(toObjectNode(propertySource));
                        }
                    }

                    public ObjectNode getIndexed() {
                        return indexed;
                    }

                    public void setIndexed(ObjectNode indexed) {
                        this.indexed = indexed;
                    }

                    public ObjectNode getPropertySource() {
                        return propertySource;
                    }

                    public void setPropertySource(ObjectNode propertySource) {
                        this.propertySource = propertySource;
                    }
                };
            }

            @Override
            public AnySchema anySchema() {
                return new AnySchema() {
                    @Override
                    public void enrichWithBeanProperty(BeanProperty beanProperty) {
                        super.enrichWithBeanProperty(beanProperty);
                        System.out.println(beanProperty.getName());
                    }
                };
            }

            @Override
            public ArraySchema arraySchema() {
                return new ArraySchema() {
                    private ObjectNode indexed;
                    private ObjectNode propertySource;

                    @Override
                    public void enrichWithBeanProperty(BeanProperty beanProperty) {
                        super.enrichWithBeanProperty(beanProperty);
                        Indexed indexed = beanProperty.getAnnotation(Indexed.class);
                        if (indexed != null) {
                            setIndexed(toObjectNode(indexed));
                        }
                        PropertySource propertySource = beanProperty.getAnnotation(PropertySource.class);
                        if (propertySource != null) {
                            setPropertySource(toObjectNode(propertySource));
                        }
                    }

                    public ObjectNode getIndexed() {
                        return indexed;
                    }

                    public void setIndexed(ObjectNode indexed) {
                        this.indexed = indexed;
                    }

                    public ObjectNode getPropertySource() {
                        return propertySource;
                    }

                    public void setPropertySource(ObjectNode propertySource) {
                        this.propertySource = propertySource;
                    }
                };
            }

            public ObjectNode toObjectNode(Indexed indexed) {
                ObjectNode node = mapper.createObjectNode();
                node.put("readonly", indexed.readonly());
                node.put("stored", indexed.stored());
                node.put("searchable", indexed.searchable());
                node.put("parse", indexed.type());
                ArrayNode copyTo = node.putArray("copyTo");
                for (String ct : indexed.copyTo()) {
                    copyTo.add(ct);
                }
                node.put("template", indexed.defaultValue());
                node.put("key", indexed.required());
                node.put("id", indexed.name());
                node.put("parse", indexed.value());
                return node;
            }

            public ObjectNode toObjectNode(PropertySource propertySource) {
                ObjectNode node = mapper.createObjectNode();
                node.put("template", propertySource.template());
                node.put("key", propertySource.key());
                node.put("id", propertySource.id());
                node.put("parse", propertySource.parse());
                node.put("unique", propertySource.unique());
                return node;
            }

        }

        public class SolrDocumentObjectSchema extends ObjectSchema {
            private ObjectNode solrDocument;
            private ObjectNode collectionSource;

            public SolrDocumentObjectSchema(JavaType convertedType) {
                Class<?> type = convertedType.getRawClass();
                SolrDocument solrDocument = type.getAnnotation(SolrDocument.class);
                if (solrDocument != null) {
                    setSolrDocument(toObjectNode(solrDocument));
                }
                CollectionSource collectionSource = type.getAnnotation(CollectionSource.class);
                if (collectionSource != null) {
                    setCollectionSource(toObjectNode(collectionSource));
                }
            }

            public ObjectNode getSolrDocument() {
                return solrDocument;
            }

            public void setSolrDocument(ObjectNode solrDocument) {
                this.solrDocument = solrDocument;
            }

            public ObjectNode getCollectionSource() {
                return collectionSource;
            }

            public void setCollectionSource(ObjectNode collectionSource) {
                this.collectionSource = collectionSource;
            }

        }

        public ObjectNode toObjectNode(SolrDocument solrDocument) {
            ObjectNode node = mapper.createObjectNode();
            node.put("collection", solrDocument.collection());
            return node;
        }

        public ObjectNode toObjectNode(CollectionSource collectionSource) {
            ObjectNode node = mapper.createObjectNode();
            node.put("key", collectionSource.key());
            return node;
        }

    }

}
