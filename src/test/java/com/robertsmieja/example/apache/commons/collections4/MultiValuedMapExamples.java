package com.robertsmieja.example.apache.commons.collections4;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class MultiValuedMapExamples {
    MultiValuedMap<Integer, String> multiMap;

    @Before
    public void setup() {
        multiMap = new HashSetValuedHashMap<>();
    }

    @Test
    public void basicMapOperations() {
        assertEquals(0, multiMap.size());

        multiMap.put(1, "foo");
        assertEquals(1, multiMap.size());

        Collection<String> strings = multiMap.get(1);
        assertFalse(strings.isEmpty());
        assertEquals(1, strings.size());

        multiMap.remove(1);
        assertEquals(0, multiMap.size());
    }

    @Test
    public void addingMultipleValues() {
        multiMap.put(1, "foo");
        assertEquals(1, multiMap.size());

        multiMap.put(1, "bar");
        assertEquals(2, multiMap.size());

        Collection<String> strings = multiMap.get(1);
        assertEquals(2, strings.size());
        assertThat(strings, containsInAnyOrder("foo", "bar"));
    }

    @Test
    public void addingAListWithAnAdd() {
        multiMap.putAll(1, Arrays.asList("foo", "bar"));
        assertEquals(2, multiMap.size());

        Collection<String> strings = multiMap.get(1);
        assertEquals(2, strings.size());
        assertThat(strings, containsInAnyOrder("foo", "bar"));
    }
}
