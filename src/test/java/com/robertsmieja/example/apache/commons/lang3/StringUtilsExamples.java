package com.robertsmieja.example.apache.commons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * All of these methods used here are null-safe
 */
public class StringUtilsExamples {

    @Test
    public void isBlankExamples() {
        assertFunction(StringUtils::isBlank, "", true);
        assertFunction(StringUtils::isBlank, null, true);
        assertFunction(StringUtils::isBlank, " ", true);
        assertFunction(StringUtils::isBlank, " This isn't blank ", false);
    }

    @Test
    public void isEmptyExamples() {
        assertFunction(StringUtils::isEmpty, "", true);
        assertFunction(StringUtils::isEmpty, null, true);
        assertFunction(StringUtils::isEmpty, " ", false);
        assertFunction(StringUtils::isEmpty, " This isn't blank ", false);
    }

    @Test
    public void capitalizationExamples() {
        assertFunction(StringUtils::capitalize, "", "");
        assertFunction(StringUtils::capitalize, " ", " ");
        assertFunction(StringUtils::capitalize, "foo", "Foo");
        assertFunction(StringUtils::capitalize, "foo bar", "Foo bar");
        assertFunction(StringUtils::capitalize, "1234", "1234");
        assertFunction(StringUtils::capitalize, null, null);
    }

    @Test
    public void uncapitalizationExamples() {
        assertFunction(StringUtils::uncapitalize, "", "");
        assertFunction(StringUtils::uncapitalize, " ", " ");
        assertFunction(StringUtils::uncapitalize, "Foo", "foo");
        assertFunction(StringUtils::uncapitalize, "Foo bar", "foo bar");
        assertFunction(StringUtils::uncapitalize, "1234", "1234");
        assertFunction(StringUtils::uncapitalize, null, null);
    }

    @Test
    public void splitExamples() {
        assertFunction(StringUtils::split, "Here are a bunch of words.", new String[]{"Here", "are", "a", "bunch", "of", "words."});
        assertFunction(StringUtils::split, "C, S, V, example", ",", new String[]{"C", " S", " V", " example"});

        //Expanded lambda expression is needed because the arguments (null, null) match multiple method signatures
        assertFunction((String str, String seperatorChar) -> StringUtils.split(str, seperatorChar), null, null, null);
    }

    @Test
    public void differenceExamples() {
        assertFunction(StringUtils::difference, "Similar string", "Similar string kindof", " kindof");
        assertFunction(StringUtils::difference, "same string", "same string", "");
        assertFunction(StringUtils::difference, "different string", "foo bar", "foo bar");
        assertFunction(StringUtils::difference, null, null, null);
    }


    /* Helpers */
    private <T, R> void assertFunction(Function<T, R> function, T input, R output) {
        assertEquals(output, function.apply(input));
    }

    private <T, R> void assertFunction(Function<T, R[]> function, T input, R[] output) {
        assertArrayEquals(output, function.apply(input));
    }

    private <T, O, R> void assertFunction(BiFunction<T, O, R> function, T input, O otherInput, R output) {
        assertEquals(output, function.apply(input, otherInput));
    }

    private <T, O, R> void assertFunction(BiFunction<T, O, R[]> function, T input, O otherInput, R[] output) {
        assertArrayEquals(output, function.apply(input, otherInput));
    }
}
