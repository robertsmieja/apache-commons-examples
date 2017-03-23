package com.robertsmieja.example.apache.commons.collections4;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * A small set of OrderedMap examples
 * <p>
 * The main benefit is that interface guarantees the implementation cares about the order.
 * <p>
 * We could use a TreeMap, but then if we ever cast down to just the Map interface,
 * we lose all knowledge that the map is order sensitive.
 */
public class OrderedMapExamples {
    OrderedMap<Integer, String> orderedMap;
    Map<Integer, String> unorderedMap;

    @Before
    public void setup() {
        orderedMap = new ListOrderedMap<>();
        unorderedMap = new HashMap<>();
    }

    @Test
    public void exampleOfHashMapBeingOutOfOrder() {
        unorderedMap.put(2, "bar");
        unorderedMap.put(3, "some really long string");
        assertEquals(2, unorderedMap.size());

        Iterator<Map.Entry<Integer, String>> iterator = unorderedMap.entrySet().iterator();

        Map.Entry<Integer, String> firstEntry = iterator.next();
        assertEquals(Integer.valueOf(2), firstEntry.getKey());
        assertEquals("bar", firstEntry.getValue());

        Map.Entry<Integer, String> secondEntry = iterator.next();
        assertEquals(Integer.valueOf(3), secondEntry.getKey());
        assertEquals("some really long string", secondEntry.getValue());

        unorderedMap.put(1, "foo");
        assertEquals(3, unorderedMap.size());

        iterator = unorderedMap.entrySet().iterator();

        firstEntry = iterator.next();
        assertEquals(Integer.valueOf(1), firstEntry.getKey());
        assertEquals("foo", firstEntry.getValue());

        secondEntry = iterator.next();
        assertEquals(Integer.valueOf(2), secondEntry.getKey());
        assertEquals("bar", secondEntry.getValue());

        Map.Entry<Integer, String> thirdEntry = iterator.next();
        assertEquals(Integer.valueOf(3), thirdEntry.getKey());
        assertEquals("some really long string", thirdEntry.getValue());
    }

    @Test
    public void exampleOfOrderedMapMaintainingOrder() {
        orderedMap.put(2, "bar");
        orderedMap.put(3, "some really long string");
        assertEquals(2, orderedMap.size());

        Iterator<Map.Entry<Integer, String>> iterator = orderedMap.entrySet().iterator();

        Map.Entry<Integer, String> firstEntry = iterator.next();
        assertEquals(Integer.valueOf(2), firstEntry.getKey());
        assertEquals("bar", firstEntry.getValue());

        Map.Entry<Integer, String> secondEntry = iterator.next();
        assertEquals(Integer.valueOf(3), secondEntry.getKey());
        assertEquals("some really long string", secondEntry.getValue());

        orderedMap.put(1, "foo");
        assertEquals(3, orderedMap.size());

        iterator = orderedMap.entrySet().iterator();

        firstEntry = iterator.next();
        assertEquals(Integer.valueOf(2), firstEntry.getKey());
        assertEquals("bar", firstEntry.getValue());

        secondEntry = iterator.next();
        assertEquals(Integer.valueOf(3), secondEntry.getKey());
        assertEquals("some really long string", secondEntry.getValue());

        Map.Entry<Integer, String> thirdEntry = iterator.next();
        assertEquals(Integer.valueOf(1), thirdEntry.getKey());
        assertEquals("foo", thirdEntry.getValue());
    }
}
