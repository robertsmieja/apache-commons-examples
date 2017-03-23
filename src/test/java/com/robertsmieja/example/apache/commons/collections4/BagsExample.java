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

    Bag<String> bag;

    @Before
    public void setup() {
        bag = new HashBag<>();
    }

    @Test
    public void simpleAdd() {
        assertEquals(0, bag.size());

        bag.add("foo");
        assertEquals(1, bag.getCount("foo"));
        assertEquals(1, bag.size());

        bag.add("foo");
        assertEquals(2, bag.getCount("foo"));
        assertEquals(2, bag.size());
    }

    @Test
    public void addingMultipleCopies() {
        assertEquals(0, bag.size());
        assertEquals(0, bag.getCount("foo"));

        bag.add("foo", 10);
        assertEquals(10, bag.size());
        assertEquals(10, bag.getCount("foo"));

        bag.add("bar");
        assertEquals(11, bag.size());
        assertEquals(1, bag.getCount("bar"));
    }

    @Test
    public void simpleRemove() {
        assertEquals(0, bag.size());
        assertEquals(0, bag.getCount("foo"));

        bag.add("foo", 10);
        assertEquals(10, bag.size());
        assertEquals(10, bag.getCount("foo"));

        bag.remove("foo");
        assertEquals(0, bag.getCount("foo"));
        assertEquals(0, bag.size());
    }

    @Test
    public void removingACertainNumberOfCopies() {
        assertEquals(0, bag.size());
        assertEquals(0, bag.getCount("foo"));

        bag.add("foo", 10);
        assertEquals(10, bag.size());
        assertEquals(10, bag.getCount("foo"));

        bag.remove("foo", 3);
        assertEquals(7, bag.getCount("foo"));
        assertEquals(7, bag.size());
    }
}
