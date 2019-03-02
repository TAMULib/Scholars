package edu.tamu.scholars.middleware;

import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_DURATION_IN_DAYS;
import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_MAX_LENGTH;
import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_MIN_LENGTH;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

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
import com.fasterxml.jackson.databind.JsonNode;
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
import com.fasterxml.jackson.module.jsonSchema.types.ArraySchema;
import com.fasterxml.jackson.module.jsonSchema.types.ObjectSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;
import com.sun.codemodel.JAnnotationArrayMember;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;

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

            @Override
            public boolean isIncludeAdditionalProperties() {
                return false;
            }
        };

        SolrDocumentSchemaFactoryWrapper solrDocumentVisitor = new SolrDocumentSchemaFactoryWrapper();

        SchemaMapper schemaMapper = new SchemaMapper(new RuleFactory(config, new SolrDocumentTypeAnnotator(config), new SchemaStore()), new SchemaGenerator());

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

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        File modelDirectory = new File(String.format("%s/source/edu/tamu/scholars/middleware/discovery/model", classpath.getFile().getAbsolutePath()));

        for (File file : modelDirectory.listFiles()) {
            System.out.println("Compiling..." + file.getAbsolutePath());
            int result = compiler.run(null, null, null, file.getAbsolutePath());
            if (result == 0) {
                System.out.println("...Success");
            }
        }

        SolrDocumentClassLoader solrDocumentClassLoader = new SolrDocumentClassLoader();

        for (BeanDefinition beanDefinition : provider.findCandidateComponents("edu.tamu.scholars.middleware.discovery.model")) {
            System.out.println("Loading..." + beanDefinition.getBeanClassName());
            Class<?> clazz = solrDocumentClassLoader.loadClass(beanDefinition.getBeanClassName());
            System.out.println("...Success... Loaded " + clazz.getSimpleName());
        }

    }

    public class SolrDocumentClassLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if (name.contains("edu.tamu.scholars.middleware.discovery.model")) {
                try {
                    String path = String.format("%s%ssource%s%s.class", classpath.getFile().getAbsolutePath(), File.separator, File.separator, name.replace(".", File.separator));
                    System.out.println(path);
                    File classFile = new File(path);
                    System.out.println(classFile.exists());
                    InputStream inputStream = new FileInputStream(classFile);

                    byte[] buffer;
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    int nextValue = 0;
                    try {
                        while ((nextValue = inputStream.read()) != -1) {
                            byteStream.write(nextValue);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buffer = byteStream.toByteArray();

                    return defineClass(name, buffer, 0, buffer.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException("", e);
                }
            }
            return super.loadClass(name);
        }
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
                    private final List<ObjectNode> annotations = new ArrayList<ObjectNode>();

                    @Override
                    public void enrichWithBeanProperty(BeanProperty beanProperty) {
                        super.enrichWithBeanProperty(beanProperty);
                        Indexed indexed = beanProperty.getAnnotation(Indexed.class);
                        if (indexed != null) {
                            annotations.add(toObjectNode(indexed));
                        }
                        PropertySource propertySource = beanProperty.getAnnotation(PropertySource.class);
                        if (propertySource != null) {
                            annotations.add(toObjectNode(propertySource));
                        }
                    }

                    public List<ObjectNode> getAnnotations() {
                        return annotations;
                    }
                };
            }

            @Override
            public ArraySchema arraySchema() {
                return new ArraySchema() {
                    private final List<ObjectNode> annotations = new ArrayList<ObjectNode>();

                    @Override
                    public void enrichWithBeanProperty(BeanProperty beanProperty) {
                        super.enrichWithBeanProperty(beanProperty);
                        Indexed indexed = beanProperty.getAnnotation(Indexed.class);
                        if (indexed != null) {
                            annotations.add(toObjectNode(indexed));
                        }
                        PropertySource propertySource = beanProperty.getAnnotation(PropertySource.class);
                        if (propertySource != null) {
                            annotations.add(toObjectNode(propertySource));
                        }
                    }

                    public List<ObjectNode> getAnnotations() {
                        return annotations;
                    }
                };
            }

            public ObjectNode toObjectNode(Indexed indexed) {
                ObjectNode node = mapper.createObjectNode();
                node.put("type", indexed.annotationType().getName());
                ObjectNode properties = node.putObject("properties");
                properties.put("readonly", indexed.readonly());
                properties.put("stored", indexed.stored());
                properties.put("searchable", indexed.searchable());
                if (!indexed.type().isEmpty()) {
                    properties.put("type", indexed.type());
                }
                if (indexed.copyTo().length > 0) {
                    ArrayNode copyTo = properties.putArray("copyTo");
                    for (String ct : indexed.copyTo()) {
                        copyTo.add(ct);
                    }
                }
                if (!indexed.defaultValue().isEmpty()) {
                    properties.put("defaultValue", indexed.defaultValue());
                }
                properties.put("required", indexed.required());
                if (!indexed.name().isEmpty()) {
                    properties.put("name", indexed.name());
                }
                if (!indexed.value().isEmpty()) {
                    properties.put("value", indexed.value());
                }
                return node;
            }

            public ObjectNode toObjectNode(PropertySource propertySource) {
                ObjectNode node = mapper.createObjectNode();
                node.put("type", propertySource.annotationType().getName());
                ObjectNode properties = node.putObject("properties");
                if (!propertySource.template().isEmpty()) {
                    properties.put("template", propertySource.template());
                }
                if (!propertySource.key().isEmpty()) {
                    properties.put("key", propertySource.key());
                }
                if (!propertySource.id().isEmpty()) {
                    properties.put("id", propertySource.id());
                }
                properties.put("parse", propertySource.parse());
                properties.put("unique", propertySource.unique());
                return node;
            }

        }

        public class SolrDocumentObjectSchema extends ObjectSchema {
            private final List<ObjectNode> annotations = new ArrayList<ObjectNode>();

            public SolrDocumentObjectSchema(JavaType convertedType) {
                Class<?> type = convertedType.getRawClass();
                SolrDocument solrDocument = type.getAnnotation(SolrDocument.class);
                if (solrDocument != null) {
                    annotations.add(toObjectNode(solrDocument));
                }
                CollectionSource collectionSource = type.getAnnotation(CollectionSource.class);
                if (collectionSource != null) {
                    annotations.add(toObjectNode(collectionSource));
                }
            }

            public List<ObjectNode> getAnnotations() {
                return annotations;
            }
        }

        public ObjectNode toObjectNode(SolrDocument solrDocument) {
            ObjectNode node = mapper.createObjectNode();
            node.put("type", solrDocument.annotationType().getName());
            ObjectNode properties = node.putObject("properties");
            if (!solrDocument.collection().isEmpty()) {
                properties.put("collection", solrDocument.collection());
            }
            return node;
        }

        public ObjectNode toObjectNode(CollectionSource collectionSource) {
            ObjectNode node = mapper.createObjectNode();
            node.put("type", collectionSource.annotationType().getName());
            ObjectNode properties = node.putObject("properties");
            if (!collectionSource.key().isEmpty()) {
                properties.put("key", collectionSource.key());
            }
            return node;
        }

    }

    public class SolrDocumentTypeAnnotator extends Jackson2Annotator {

        public SolrDocumentTypeAnnotator(GenerationConfig generationConfig) {
            super(generationConfig);
        }

        @Override
        public void propertyInclusion(JDefinedClass clazz, JsonNode schema) {
            super.propertyInclusion(clazz, schema);
            JsonNode additionalProperties = schema.get("additionalProperties");
            if (additionalProperties != null) {
                JsonNode annotations = additionalProperties.get("annotations");
                if (annotations != null) {
                    if (annotations.isArray()) {
                        for (final JsonNode annotation : annotations) {
                            String annotationType = annotation.get("type").asText();
                            try {
                                JClass annotationClass = new JCodeModel().ref(Class.forName(annotationType));
                                JAnnotationUse annotationUse = clazz.annotate(annotationClass);
                                annotation.get("properties").fields().forEachRemaining(pEntry -> {
                                    if (pEntry.getValue().isArray()) {
                                        JAnnotationArrayMember arrayMember = annotationUse.paramArray(pEntry.getKey());
                                        for (final JsonNode value : pEntry.getValue()) {
                                            if (value.isTextual()) {
                                                arrayMember.param(value.asText());
                                            } else if (value.isBoolean()) {
                                                arrayMember.param(value.asBoolean());
                                            } else if (value.isInt()) {
                                                arrayMember.param(value.asInt());
                                            }
                                        }
                                    } else {
                                        String key = pEntry.getKey();
                                        JsonNode value = pEntry.getValue();
                                        if (value.isTextual()) {
                                            annotationUse.param(key, value.asText());
                                        } else if (value.isBoolean()) {
                                            annotationUse.param(key, value.asBoolean());
                                        } else if (value.isInt()) {
                                            annotationUse.param(key, value.asInt());
                                        }
                                    }
                                });
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
            super.propertyField(field, clazz, propertyName, propertyNode);
            JsonNode annotations = propertyNode.get("annotations");
            if (annotations != null) {
                if (annotations.isArray()) {
                    for (final JsonNode annotation : annotations) {
                        String annotationType = annotation.get("type").asText();
                        try {
                            JClass annotationClass = new JCodeModel().ref(Class.forName(annotationType));
                            JAnnotationUse annotationUse = field.annotate(annotationClass);
                            annotation.get("properties").fields().forEachRemaining(pEntry -> {
                                if (pEntry.getValue().isArray()) {
                                    JAnnotationArrayMember arrayMember = annotationUse.paramArray(pEntry.getKey());
                                    for (final JsonNode value : pEntry.getValue()) {
                                        if (value.isTextual()) {
                                            arrayMember.param(value.asText());
                                        } else if (value.isBoolean()) {
                                            arrayMember.param(value.asBoolean());
                                        } else if (value.isInt()) {
                                            arrayMember.param(value.asInt());
                                        }
                                    }
                                } else {
                                    String key = pEntry.getKey();
                                    JsonNode value = pEntry.getValue();
                                    if (value.isTextual()) {
                                        annotationUse.param(key, value.asText());
                                    } else if (value.isBoolean()) {
                                        annotationUse.param(key, value.asBoolean());
                                    } else if (value.isInt()) {
                                        annotationUse.param(key, value.asInt());
                                    }
                                }
                            });
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

    }

}
