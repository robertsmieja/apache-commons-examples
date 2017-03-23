package com.robertsmieja.example.apache.commons.collections4;

import org.apache.commons.collections4.map.LRUMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Examples demonstrating the LRUMap
 */
public class LRUMapExamples {
    LRUMap<Integer, String> lruMap;

    @Before
    public void setup() {
        lruMap = new LRUMap<>(2);
    }

    @Test
    public void maxSize() {
        assertEquals(lruMap.size(), 0);
        assertEquals(lruMap.maxSize(), 2);
    }

    @Test
    public void anythingUnderTheMaxSizeStaysInTheMap() {
        lruMap.put(1, "foo");
        lruMap.put(2, "bar");

        assertEquals(lruMap.get(1), "foo");
        assertEquals(lruMap.get(2), "bar");
    }

    @Test
    public void goingOverTheMaxKicksOutTheOldestEntry() {
        lruMap.put(1, "foo");
        lruMap.put(2, "bar");

        assertEquals(lruMap.get(1), "foo");
        assertEquals(lruMap.get(2), "bar");

        //A put now show remove the entry (1, "foo")
        lruMap.put(3, "is it gone?");

        assertFalse(lruMap.containsKey(1));
        assertNull(lruMap.get(1));
        assertEquals(lruMap.size(), 2);

        assertEquals(lruMap.get(2), "bar");
        assertEquals(lruMap.get(3), "is it gone?");
    }

    @Test
    public void goingOverTheMaxKicksOutTheOldestEntry_exceptThisTimeWithTheSecondEntry() {
        lruMap.put(1, "foo");
        lruMap.put(2, "bar");

        assertEquals(lruMap.get(2), "bar");
        assertEquals(lruMap.get(1), "foo");

        //A put now show remove the entry (1, "foo")
        lruMap.put(3, "is it gone?");

        assertFalse(lruMap.containsKey(2));
        assertNull(lruMap.get(2));
        assertEquals(lruMap.size(), 2);

        assertEquals(lruMap.get(1), "foo");
        assertEquals(lruMap.get(3), "is it gone?");
    }

    @Test
    public void passingInFalseDoesntUpdateTheInternalMru() {
        lruMap.put(1, "foo");
        lruMap.put(2, "bar");

        assertEquals(lruMap.get(2), "bar");
        assertEquals(lruMap.get(1, false), "foo");

        //A put now show remove the entry (1, "foo")
        lruMap.put(3, "is it gone?");

        assertFalse(lruMap.containsKey(1));
        assertNull(lruMap.get(1));
        assertEquals(lruMap.size(), 2);

        assertEquals(lruMap.get(2), "bar");
        assertEquals(lruMap.get(3), "is it gone?");
    }
}
