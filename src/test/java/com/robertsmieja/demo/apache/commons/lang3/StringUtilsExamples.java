package com.robertsmieja.demo.apache.commons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * This class contains example usages of StringUtils
 */
public class StringUtilsExamples {

    @Test
    public void capitalizationExamples() {
        //This is a list that contains a Pair of 'expected', 'actual' results
        List<Pair<String, String>> listOfStringPairs = Arrays.asList(
                new ImmutablePair<>("", ""),
                new ImmutablePair<>(" ", " "),
                new ImmutablePair<>("Foo", "foo"),
                new ImmutablePair<>("Foo bar", "foo bar"),
                new ImmutablePair<>("1234", "1234")
        );
        //This flips the right and left values so we can test a reverse operation
        List<Pair<String, String>> flippedListOfStringPairs = flipListOfPairs(listOfStringPairs);

        assertFunctionMatchesList(listOfStringPairs, StringUtils::capitalize);
        assertFunctionMatchesList(flippedListOfStringPairs, StringUtils::uncapitalize);
    }


    private void assertFunctionMatchesList(List<Pair<String, String>> pairList, Function<String, String> function) {
        pairList.stream().forEach((pair) -> assertFunctionMatchesPair(pair, function));
    }

    private void assertFunctionMatchesPair(Pair<String, String> expectedAndInputPair, Function<String, String> function) {
        assertEquals(expectedAndInputPair.getLeft(), function.apply(expectedAndInputPair.getRight()));
    }

    private <T> List<Pair<T, T>> flipListOfPairs(List<Pair<T, T>> listOfPairs) {
        return
                listOfPairs.stream()
                        .map(pair -> new ImmutablePair<>(pair.getRight(), pair.getLeft()))
                        .collect(Collectors.toList());
    }
}
