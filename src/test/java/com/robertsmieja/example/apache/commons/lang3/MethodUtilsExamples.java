package com.robertsmieja.example.apache.commons.lang3;

import org.apache.commons.beanutils.MethodUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class MethodUtilsExamples {

    @Test
    public void wrapAndUnwrapPrimitives() {
        assertEquals(int.class, MethodUtils.getPrimitiveType(Integer.class));
        assertEquals(Integer.class, MethodUtils.getPrimitiveWrapper(int.class));
    }

    @Test
    public void getAccessibleMethod_noArgs() {
        Method publicPrint = MethodUtils.getAccessibleMethod(ClassWithMethods.class, "publicPrint", new Class[]{});
        assertNotNull(publicPrint);

        assertEquals("publicPrint", publicPrint.getName());
        assertEquals(void.class, publicPrint.getReturnType());
    }

    @Test
    public void getAccessibleMethod_withArgs() {
        Method add = MethodUtils.getAccessibleMethod(ClassWithMethods.class, "add", new Class[]{int.class, int.class});
        assertNotNull(add);

        assertEquals("add", add.getName());
        assertEquals(int.class, add.getReturnType());
    }

    @Test
    public void invokePublicPrint() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ClassWithMethods object = new ClassWithMethods();
        MethodUtils.invokeExactMethod(object, "publicPrint", null);
    }

    @Test
    public void invokePrivatePrint() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //This will fail
        ClassWithMethods object = new ClassWithMethods();
        try {
            MethodUtils.invokeExactMethod(object, "privatePrint", null);
            fail("Exception expected");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //The work around is use "normal" Java reflection
        Method privatePrint = object.getClass().getDeclaredMethod("privatePrint");
        privatePrint.setAccessible(true); //SecurityManager can prevent this method from being used!
        privatePrint.invoke(object);
        privatePrint.setAccessible(false);
    }

    @Test
    public void invokeAdd() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ClassWithMethods object = new ClassWithMethods();
        int sum = (int) MethodUtils.invokeExactMethod(object, "add", new Object[]{1, 2}, new Class[]{int.class, int.class});

        assertEquals(3, sum);
    }

    public static class ClassWithMethods {
        private void privatePrint() {
            System.out.println("privatePrint() called!");
        }

        public void publicPrint() {
            System.out.println("publicPrint() called!");
        }

        public int add(int x, int y) {
            return x + y;
        }
    }
}
