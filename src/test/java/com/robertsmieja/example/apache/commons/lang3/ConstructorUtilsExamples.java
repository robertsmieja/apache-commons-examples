package com.robertsmieja.example.apache.commons.lang3;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertNotNull;

public class ConstructorUtilsExamples {

    public static class CoolClassWithConstructors {
        public CoolClassWithConstructors(){}
        public CoolClassWithConstructors(String stringArg){}
        public CoolClassWithConstructors(int intArg) {}
    }


    @Test
    public void constructorUtilsExample() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<CoolClassWithConstructors> emptyCosntructor = ConstructorUtils.getAccessibleConstructor(CoolClassWithConstructors.class);
        Constructor<CoolClassWithConstructors> stringConstructor = ConstructorUtils.getAccessibleConstructor(CoolClassWithConstructors.class, String.class);
        Constructor<CoolClassWithConstructors> intConstructor = ConstructorUtils.getAccessibleConstructor(CoolClassWithConstructors.class, int.class);

        assertNotNull(emptyCosntructor);
        assertNotNull(stringConstructor);
        assertNotNull(intConstructor);

        CoolClassWithConstructors instance = emptyCosntructor.newInstance();
        assertNotNull(instance);

        CoolClassWithConstructors emptyConstructorInstance = ConstructorUtils.invokeConstructor(CoolClassWithConstructors.class);
        CoolClassWithConstructors stringConstructorInstance = ConstructorUtils.invokeConstructor(CoolClassWithConstructors.class, "Argument");
        CoolClassWithConstructors intConstructorInstance = ConstructorUtils.invokeConstructor(CoolClassWithConstructors.class, 1);

        assertNotNull(emptyConstructorInstance);
        assertNotNull(stringConstructorInstance);
        assertNotNull(intConstructorInstance);
    }
}
