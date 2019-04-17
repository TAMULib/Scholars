package edu.tamu.scholars.middleware.discovery.serializer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.tamu.scholars.middleware.discovery.annotation.NestedObject;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject.Reference;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;
import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

public abstract class AbstractSolrDocumentSerializer<D extends AbstractSolrDocument> extends JsonSerializer<D> {

    private final static String ID_PROPERTY_NAME = "id";

    private final static String LABEL_PROPERTY_NAME = "label";

    private final static String NESTED_ID_DELIMITER = "::";

    // TODO: rewrite, reduce redundancy, use recursion to supported n depth nested objects

    @Override
    public void serialize(D document, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();

        // serialize all non nested fields
        for (Field field : FieldUtils.getFieldsListWithAnnotation(document.getClass(), PropertySource.class)) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(document);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value != null) {
                String name = field.getName();
                PropertySource source = field.getAnnotation(PropertySource.class);
                if (!source.nested()) {
                    jsonGenerator.writeObjectField(name, value);
                }
            }
        }

        for (Field field : FieldUtils.getFieldsListWithAnnotation(document.getClass(), NestedObject.class)) {
            field.setAccessible(true);

            Object value = null;
            try {
                value = field.get(document);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }

            if (value != null) {
                String name = field.getName();

                NestedObject references = field.getAnnotation(NestedObject.class);

                if (List.class.isAssignableFrom(value.getClass())) {
                    @SuppressWarnings("unchecked")
                    List<String> values = (List<String>) value;

                    ArrayNode array = JsonNodeFactory.instance.arrayNode();

                    for (String v : values) {

                        ObjectNode node = JsonNodeFactory.instance.objectNode();

                        String[] vParts = v.split(NESTED_ID_DELIMITER);

                        node.put(LABEL_PROPERTY_NAME, vParts[0]);

                        if (vParts.length == 2) {
                            node.put(ID_PROPERTY_NAME, vParts[1]);

                            for (Reference reference : references.value()) {
                                String ref = reference.value();
                                String property = reference.key();

                                Field nestedField = FieldUtils.getField(document.getClass(), ref, true);

                                Object nestedValue = null;
                                try {
                                    nestedValue = nestedField.get(document);
                                } catch (IllegalArgumentException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }

                                if (nestedValue != null) {

                                    @SuppressWarnings("unchecked")
                                    List<String> nestedValues = (List<String>) nestedValue;

                                    for (String nv : nestedValues) {
                                        if (nv.contains(vParts[1])) {

                                            String[] nvParts = nv.split(NESTED_ID_DELIMITER);

                                            if (nvParts.length == 3) {

                                                ObjectNode subNode = JsonNodeFactory.instance.objectNode();

                                                subNode.put(LABEL_PROPERTY_NAME, nvParts[0]);

                                                subNode.put(ID_PROPERTY_NAME, nvParts[2]);

                                                node.set(property, subNode);

                                            } else {
                                                node.put(property, nvParts[0]);
                                            }

                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        array.add(node);
                    }

                    jsonGenerator.writeObjectField(name, array);

                } else {

                    ObjectNode node = JsonNodeFactory.instance.objectNode();

                    String v = value.toString();

                    String[] vParts = v.split(NESTED_ID_DELIMITER);

                    node.put(LABEL_PROPERTY_NAME, vParts[0]);

                    if (vParts.length == 2) {
                        node.put(ID_PROPERTY_NAME, vParts[1]);

                        for (Reference reference : references.value()) {
                            String ref = reference.value();
                            String property = reference.key();

                            Field nestedField = FieldUtils.getField(document.getClass(), ref, true);

                            Object nestedValue = null;
                            try {
                                nestedValue = nestedField.get(document);
                            } catch (IllegalArgumentException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                            if (nestedValue != null) {

                                String nv = nestedValue.toString();

                                String[] nvParts = nv.split(NESTED_ID_DELIMITER);

                                if (nvParts.length == 3) {

                                    ObjectNode subNode = JsonNodeFactory.instance.objectNode();

                                    subNode.put(LABEL_PROPERTY_NAME, nvParts[0]);

                                    subNode.put(ID_PROPERTY_NAME, nvParts[2]);

                                    node.set(property, subNode);

                                } else {
                                    node.put(property, nvParts[0]);
                                }

                            }
                        }

                    }
                }
            }
        }

        jsonGenerator.writeEndObject();
    }

}
