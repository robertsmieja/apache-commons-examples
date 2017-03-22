package com.robertsmieja.example.apache.commons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This class contains example usages of StringUtils
 */
public class StringUtilsExamples {

    @Test
    public void isBlankExamples(){
        assertFunction(StringUtils::isBlank, "", true);
        assertFunction(StringUtils::isBlank, null, true);
        assertFunction(StringUtils::isBlank, " ", true);
        assertFunction(StringUtils::isBlank, " This isn't blank ", false);
    }

    @Test
    public void isEmptyExamples(){
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
    }

    @Test
    public void uncapitalizationExamples() {
        assertFunction(StringUtils::uncapitalize, "", "");
        assertFunction(StringUtils::uncapitalize, " ", " ");
        assertFunction(StringUtils::uncapitalize, "Foo", "foo");
        assertFunction(StringUtils::uncapitalize, "Foo bar", "foo bar");
        assertFunction(StringUtils::uncapitalize, "1234", "1234");
    }

    @Test
    public void splitExamples(){
        assertFunction(StringUtils::split, "Here are a bunch of words.", new String[]{"Here", "are", "a", "bunch", "of", "words."});
        assertFunction(StringUtils::split, "C, S, V, example",",", new String[]{"C", " S", " V", " example"});
    }

    @Test
    public void differenceExamples(){
        assertFunction(StringUtils::difference, "Similar string", "Similar string kindof", " kindof");
        assertFunction(StringUtils::difference, "same string", "same string", "");
        assertFunction(StringUtils::difference, "different string", "foo bar", "foo bar");
    }










    /* Helpers */
    private <T, R> void assertFunction(Function<T, R> function, T input, R output){
        if (output instanceof Object[]){
            assertArrayEquals((R[])output, (R[]) function.apply(input));
        } else {
            assertEquals(output, function.apply(input));
        }
    }
    private <T, O, R> void assertFunction(BiFunction<T, O, R> function, T input, O otherInput, R output){
        if (output instanceof Object[]){
            assertArrayEquals((R[])output, (R[]) function.apply(input, otherInput));
        } else {
            assertEquals(output, function.apply(input,otherInput));
        }
    }
}
