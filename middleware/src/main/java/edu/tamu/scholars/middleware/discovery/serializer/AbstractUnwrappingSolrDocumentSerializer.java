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
import com.fasterxml.jackson.databind.util.NameTransformer;

import edu.tamu.scholars.middleware.discovery.annotation.NestedMultiValuedProperty;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject.Reference;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;
import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

public abstract class AbstractUnwrappingSolrDocumentSerializer<D extends AbstractSolrDocument> extends JsonSerializer<D> {

    private final static String ID_PROPERTY_NAME = "id";

    private final static String LABEL_PROPERTY_NAME = "label";

    private final static String NESTED_ID_DELIMITER = "::";

    private final NameTransformer nameTransformer;

    public AbstractUnwrappingSolrDocumentSerializer(final NameTransformer nameTransformer) {
        this.nameTransformer = nameTransformer;
    }

    @Override
    public boolean isUnwrappingSerializer() {
        return true;
    }

    @Override
    public void serialize(D document, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        jsonGenerator.writeObjectField(nameTransformer.transform(ID_PROPERTY_NAME), document.getId());

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
                NestedObject nested = field.getAnnotation(NestedObject.class);
                if (nested != null) {
                    if (List.class.isAssignableFrom(value.getClass())) {
                        @SuppressWarnings("unchecked")
                        List<String> values = (List<String>) value;
                        ArrayNode array = JsonNodeFactory.instance.arrayNode();
                        for (String v : values) {
                            String[] vParts = v.split(NESTED_ID_DELIMITER);
                            if (vParts.length > 1) {
                                array.add(processValue(document, field, vParts, 1));
                            }
                        }
                        if(array.size() > 0) {
                            jsonGenerator.writeObjectField(nameTransformer.transform(name), array);
                        }
                    } else {
                        String v = value.toString();
                        String[] vParts = v.split(NESTED_ID_DELIMITER);
                        if (vParts.length > 1) {
                            ObjectNode node = processValue(document, field, vParts, 1);
                            jsonGenerator.writeObjectField(nameTransformer.transform(name), node);
                        }
                    }
                } else {
                    if (!value.toString().contains(NESTED_ID_DELIMITER)) {
                        jsonGenerator.writeObjectField(nameTransformer.transform(name), value);
                    }
                }
            }
        }
    }

    private ObjectNode processValue(D document, Field field, String[] vParts, int index) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put(LABEL_PROPERTY_NAME, vParts[0]);
        node.put(ID_PROPERTY_NAME, vParts[index]);
        processNestedReferences(document, field, node, vParts, index + 1);
        return node;
    }

    private void processNestedReferences(D document, Field field, ObjectNode node, String[] vParts, int depth) {
        NestedObject references = field.getAnnotation(NestedObject.class);
        if (references != null) {
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
                    if (List.class.isAssignableFrom(nestedValue.getClass())) {
                        @SuppressWarnings("unchecked")
                        List<String> nestedValues = (List<String>) nestedValue;
                        ArrayNode array = JsonNodeFactory.instance.arrayNode();
                        boolean multiValued = nestedField.getAnnotation(NestedMultiValuedProperty.class) != null;
                        for (String nv : nestedValues) {
                            if (nv.contains(vParts[depth - 1])) {
                                String[] nvParts = nv.split(NESTED_ID_DELIMITER);
                                if (nvParts.length > depth) {
                                    ObjectNode subNode = processValue(document, nestedField, nvParts, depth);
                                    array.add(subNode);
                                } else {
                                    if (nvParts[0] != null) {
                                        array.add(nvParts[0]);
                                    }
                                }
                                if (!multiValued) {
                                    break;
                                }
                            }
                        }
                        if (array.size() > 0) {
                            if (multiValued) {
                                node.set(property, array);
                            } else {
                                node.set(property, array.get(0));
                            }
                        }
                    } else {
                        String nv = nestedValue.toString();
                        String[] nvParts = nv.split(NESTED_ID_DELIMITER);
                        if (nvParts.length > depth) {
                            ObjectNode subNode = processValue(document, nestedField, nvParts, depth);
                            node.set(property, subNode);
                        } else {
                            if (nvParts[0] != null) {
                                node.put(property, nvParts[0]);
                            }
                        }
                    }
                }
            }
        }
    }

}
