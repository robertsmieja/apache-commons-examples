package com.robertsmieja.example.apache.commons.collections4;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example of Bags interface
 * <p>
 * NOTE: This violates the Collections interface contracts for almost every operation
 */
public class BagsExample {

    Bag<Integer> integerBag;

    @Before
    public void setup() {
        integerBag = new HashBag<>();
    }

    @Test
    public void simpleAdd() {
        assertEquals(0, integerBag.size());

        integerBag.add(1);
        assertEquals(1, integerBag.getCount(1));
        assertEquals(1, integerBag.size());

        integerBag.add(1);
        assertEquals(2, integerBag.getCount(1));
        assertEquals(2, integerBag.size());
    }

    @Test
    public void addingMultipleCopies() {
        assertEquals(0, integerBag.size());
        assertEquals(0, integerBag.getCount(1));

        integerBag.add(1, 10);
        assertEquals(10, integerBag.size());
        assertEquals(10, integerBag.getCount(1));

        integerBag.add(2);
        assertEquals(11, integerBag.size());
        assertEquals(1, integerBag.getCount(2));
    }

    @Test
    public void simpleRemove() {
        assertEquals(0, integerBag.size());
        assertEquals(0, integerBag.getCount(1));

        integerBag.add(1, 10);
        assertEquals(10, integerBag.size());
        assertEquals(10, integerBag.getCount(1));

        integerBag.remove(1);
        assertEquals(0, integerBag.getCount(1));
        assertEquals(0, integerBag.size());
    }

    @Test
    public void removingACertainNumberOfCopies() {
        assertEquals(0, integerBag.size());
        assertEquals(0, integerBag.getCount(1));

        integerBag.add(1, 10);
        assertEquals(10, integerBag.size());
        assertEquals(10, integerBag.getCount(1));

        integerBag.remove(1, 3);
        assertEquals(7, integerBag.getCount(1));
        assertEquals(7, integerBag.size());
    }
}
