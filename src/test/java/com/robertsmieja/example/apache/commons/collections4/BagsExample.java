package com.robertsmieja.example.apache.commons.collections4;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * NOTE: This violates the Collections interface contracts for almost every operation
 */
public class BagsExample {

    Bag<String> integerBag;

    @Before
    public void setup() {
        integerBag = new HashBag<>();
    }

    @Test
    public void simpleAdd() {
        assertEquals(0, integerBag.size());

        integerBag.add("foo");
        assertEquals(1, integerBag.getCount("foo"));
        assertEquals(1, integerBag.size());

        integerBag.add("foo");
        assertEquals(2, integerBag.getCount("foo"));
        assertEquals(2, integerBag.size());
    }

    @Test
    public void addingMultipleCopies() {
        assertEquals(0, integerBag.size());
        assertEquals(0, integerBag.getCount("foo"));

        integerBag.add("foo", 10);
        assertEquals(10, integerBag.size());
        assertEquals(10, integerBag.getCount("foo"));

        integerBag.add("bar");
        assertEquals(11, integerBag.size());
        assertEquals(1, integerBag.getCount("bar"));
    }

    @Test
    public void simpleRemove() {
        assertEquals(0, integerBag.size());
        assertEquals(0, integerBag.getCount("foo"));

        integerBag.add("foo", 10);
        assertEquals(10, integerBag.size());
        assertEquals(10, integerBag.getCount("foo"));

        integerBag.remove("foo");
        assertEquals(0, integerBag.getCount("foo"));
        assertEquals(0, integerBag.size());
    }

    @Test
    public void removingACertainNumberOfCopies() {
        assertEquals(0, integerBag.size());
        assertEquals(0, integerBag.getCount("foo"));

        integerBag.add("foo", 10);
        assertEquals(10, integerBag.size());
        assertEquals(10, integerBag.getCount("foo"));

        integerBag.remove("foo", 3);
        assertEquals(7, integerBag.getCount("foo"));
        assertEquals(7, integerBag.size());
    }
}
