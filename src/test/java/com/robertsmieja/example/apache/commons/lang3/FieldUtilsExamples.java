package com.robertsmieja.example.apache.commons.lang3;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

public class FieldUtilsExamples {

    public static class ClassWithFields {
        public String publicString = "foo";
        private Boolean encapsulatedField = true;
        private long secretField = 1_234L;

        @CoolAnnotation
        private String annotatedField = "bar";

        public ClassWithFields() {
        }

        public ClassWithFields(long secretField) {
            this.secretField = secretField;
        }

        public Boolean getEncapsulatedField() {
            return encapsulatedField;
        }

        public void setEncapsulatedField(Boolean encapsulatedField) {
            this.encapsulatedField = encapsulatedField;
        }

        public String getAnnotatedField() {
            return annotatedField;
        }

        public void setAnnotatedField(String annotatedField) {
            this.annotatedField = annotatedField;
        }
    }

    @Test
    public void getAllAnnotatedFields() {
        Field[] fieldsWithAnnotation = FieldUtils.getFieldsWithAnnotation(ClassWithFields.class, CoolAnnotation.class);
        List<Field> fieldsListWithAnnotation = FieldUtils.getFieldsListWithAnnotation(ClassWithFields.class, CoolAnnotation.class);

        assertEquals(1, fieldsWithAnnotation.length);
        assertEquals(1, fieldsListWithAnnotation.size());
        assertEquals(fieldsWithAnnotation[0], fieldsListWithAnnotation.get(0));

        Field field = fieldsWithAnnotation[0];
        assertEquals("annotatedField", field.getName());
    }

    @Test
    public void getAllFields(){
        Field[] allFields = FieldUtils.getAllFields(ClassWithFields.class);
        List<String> allFieldNames = Arrays.stream(allFields).map(Field::getName).collect(Collectors.toList());

        assertThat(allFieldNames, hasItem("publicString"));
        assertThat(allFieldNames, hasItem("encapsulatedField"));
        assertThat(allFieldNames, hasItem("secretField"));
        assertThat(allFieldNames, hasItem("annotatedField"));
    }

    @Test
    public void readAndWritePublicField() throws IllegalAccessException {
        Field publicString = FieldUtils.getDeclaredField(ClassWithFields.class, "publicString");

        ClassWithFields object = new ClassWithFields();
        assertSame(object.publicString, FieldUtils.readField(publicString, object));
        assertEquals("foo", FieldUtils.readField(publicString, object));

        FieldUtils.writeField(publicString, object, "notFoo");
        assertEquals("notFoo", object.publicString);
        assertSame(object.publicString, FieldUtils.readField(publicString, object));
    }

    @Test
    public void readAndWritePrivateField_withoutForcingAccess() throws IllegalAccessException {
        //This will fail!
        Field secretField = FieldUtils.getDeclaredField(ClassWithFields.class, "secretField");
        assertNull(secretField);
    }

    @Test
    public void readAndWritePrivateField() throws IllegalAccessException {
        //passing in true will make it work
        Field secretField = FieldUtils.getField(ClassWithFields.class, "secretField", true);
        assertNotNull(secretField);

        ClassWithFields object = new ClassWithFields();
        assertEquals(1234L, FieldUtils.readField(secretField, object, true));

        FieldUtils.writeField(secretField, object, 1L);
        assertEquals(1L, FieldUtils.readField(secretField, object, true));

    }
}
