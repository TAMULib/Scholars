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
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.sun.codemodel.JCodeModel;

import edu.tamu.scholars.middleware.auth.config.AuthConfig;
import edu.tamu.scholars.middleware.auth.config.PasswordConfig;
import edu.tamu.scholars.middleware.config.MiddlewareConfig;

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

        JsonSchemaGenerator jsonSchemaGenerator = new JsonSchemaGenerator(objectMapper);

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

        SchemaMapper schemaMapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);

        provider.addIncludeFilter(new AnnotationTypeFilter(SolrDocument.class));

        for (BeanDefinition beanDefinition : provider.findCandidateComponents("edu.tamu.scholars.middleware.discovery.model")) {
            Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());

            JsonSchema jsonSchema = jsonSchemaGenerator.generateSchema(clazz);

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

}
