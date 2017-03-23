package com.robertsmieja.example.apache.commons.collections4;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Examples relating to HashedMap and MapIterator, mostly focusing on MapIterator
 * <p>
 * The benefit of using HashedMap instead of the default HashMap is access to the mapIterator()
 * <p>
 * The main benefits to using MapIterator are performance, ease-of-use when doing value changes.
 * You also get the option of using an Iterator
 */
public class MapIteratorExamples {

    HashedMap<Integer, String> totallyNotAHashMap;

    @Before
    public void setup() {
        totallyNotAHashMap = new HashedMap<>();

        totallyNotAHashMap.put(1, "foo");
        totallyNotAHashMap.put(2, "bar");
        totallyNotAHashMap.put(3, "it's actually a hash map");

        assertEquals(3, totallyNotAHashMap.size());
    }

    @Test
    public void readingWithMapIterator() {
        MapIterator<Integer, String> iterator = totallyNotAHashMap.mapIterator();

        while (iterator.hasNext()) {
            Integer key = iterator.next();
            String value = iterator.getValue();
            System.out.println("Key: <" + key + ">, Value: <" + value + ">");
        }
    }

    @Test
    public void writingWithMapIterator() {
        MapIterator<Integer, String> iterator = totallyNotAHashMap.mapIterator();

        while (iterator.hasNext()) {
            Integer key = iterator.next();
            iterator.setValue("Updated! <" + key + ">");
        }

        iterator = totallyNotAHashMap.mapIterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            String value = iterator.getValue();
            System.out.println("Key: <" + key + ">, Value: <" + value + ">");
        }
    }

    @Test
    public void readingWithMapEntry() {
        Set<Map.Entry<Integer, String>> entries = totallyNotAHashMap.entrySet();

        for (Map.Entry<Integer, String> entry : entries) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Key: <" + key + ">, Value: <" + value + ">");
        }
    }

    @Test
    public void writingWithMapEntry() {
        Set<Map.Entry<Integer, String>> entries = totallyNotAHashMap.entrySet();

        for (Map.Entry<Integer, String> entry : entries) {
            Integer key = entry.getKey();
            totallyNotAHashMap.put(key, "Updated! <" + key + ">");
        }

        for (Map.Entry<Integer, String> entry : entries) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Key: <" + key + ">, Value: <" + value + ">");
        }
    }
}
