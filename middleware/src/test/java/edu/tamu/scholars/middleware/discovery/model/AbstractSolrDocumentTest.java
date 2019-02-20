package edu.tamu.scholars.middleware.discovery.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public abstract class AbstractSolrDocumentTest<D extends AbstractSolrDocument> {

    @Test
    public void testDefaultConstructor() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Class<?> clazz = getType();

        @SuppressWarnings("unchecked")
        D document = (D) clazz.getConstructor().newInstance();

        assertNotNull(document);
    }

    @Test
    public void testGettersAndSetters() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        Class<?> clazz = getType();

        @SuppressWarnings("unchecked")
        D document = (D) clazz.getConstructor().newInstance();

        String single = "Test";
        List<String> multiple = Arrays.asList(new String[] { "Hello", "World" });

        // NOTE: only gets field annotated with @Indexed, which is all fields of a AbstractSolrDocument
        for (Field field : FieldUtils.getFieldsListWithAnnotation(clazz, Indexed.class)) {
            String property = field.getName();
            if (Collection.class.isAssignableFrom(field.getType())) {
                MethodUtils.invokeMethod(document, true, setter(property), multiple);
                assertEquals(multiple, MethodUtils.invokeMethod(document, true, getter(property)));
            } else {
                MethodUtils.invokeMethod(document, true, setter(property), single);
                assertEquals(single, MethodUtils.invokeMethod(document, true, getter(property)));
            }
        }
    }

    private String getter(String property) {
        return getAccessor("get", property);
    }

    private String setter(String property) {
        return getAccessor("set", property);
    }

    private String getAccessor(String accessor, String property) {
        return accessor + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
    }

    protected abstract Class<?> getType();

}
