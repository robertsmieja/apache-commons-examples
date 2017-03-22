package com.robertsmieja.example.apache.commons.lang3;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

/**
 * This class contains example usages of Pairs and Tuples
 */
public class PairAndTupleExamples {
    MutablePair<Integer, String> mutablePair;
    Pair<Integer, List<String>> immutablePair;


    @Before
    public void setup() {
        mutablePair = new MutablePair<>(100, "mutableFoo");

        ArrayList<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("immutableFoo");

        immutablePair = new ImmutablePair<>(200, listOfStrings);
    }

    @Test
    public void basicPairExample() {
        //Pair is the common interface between MutablePair and ImmutablePair
        Pair<Integer, String> pair = mutablePair;

        //To get the values back out of the pair, we can do so like this:
        assertEquals(Integer.valueOf(100), pair.getLeft());
        assertEquals("mutableFoo", pair.getRight());

        //Since Pair implements Map.Entry, we can use the follow methods as well:
        assertEquals(Integer.valueOf(100), pair.getKey());
        assertEquals("mutableFoo", pair.getValue());
    }

    @Test
    public void mutablePairExample() {
        //Mutable pairs let us change values
        //The interface Pair does not have the following methods:
        mutablePair.setLeft(101);
        mutablePair.setRight("notTheSameFoo");

        assertEquals(Integer.valueOf(101), mutablePair.getLeft());
        assertEquals("notTheSameFoo", mutablePair.getRight());
    }

    @Test
    public void immutablePairExample() {
        //To get the values back out of the pair, we can do so like this:
        assertEquals(Integer.valueOf(200), immutablePair.getLeft());
        assertThat(immutablePair.getRight(), containsInAnyOrder("immutableFoo"));

        try {
            //The setLeft and setRight methods do not exist, and if we try to call setValue we will get an exception
            immutablePair.setValue(Arrays.asList("this won't work"));
            fail("Exception expected");
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }

        //However an ImmutablePair is only as immutable as the objects in it
        //For example, we can modify the list in the pair, so it's not really an ImmutablePair
        immutablePair.getRight().add("mutableFoo");

        //Verify that the pair was mutated
        assertThat(immutablePair.getRight(), containsInAnyOrder("immutableFoo", "mutableFoo"));
    }
}
