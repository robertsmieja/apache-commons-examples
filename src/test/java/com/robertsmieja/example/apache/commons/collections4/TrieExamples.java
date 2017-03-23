package com.robertsmieja.example.apache.commons.collections4;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.SortedMap;

/**
 * Examples of Trie, which is a type of "search tree".
 * It's an ordered map where each "node" has prefixes based on the key.
 * <p>
 * A PATRICIA Trie is the only read/write implementation included with Apache Commons,
 * and is a "compact prefix tree" since it stores information in nodes other than the leaves.
 */
public class TrieExamples {
    Trie<String, String> trie;

    @Before
    public void setup() {
        trie = new PatriciaTrie<>();

        trie.put("Awesome", "value");
        trie.put("Awe", "some");
        trie.put("awesome", "lowercase value");
        trie.put("different", "keyValuePair");
        trie.put("Awesome Key", "with an Awesome Value");
        trie.put("A", "B");
    }

    @Test
    public void triePrefixMap() {
        SortedMap<String, String> prefixMap = trie.prefixMap("A");
        printPrefixMap(prefixMap);

        System.out.println();

        prefixMap = trie.prefixMap("Awe");
        printPrefixMap(prefixMap);
    }

    private void printPrefixMap(SortedMap<String, String> prefixMap) {
        System.out.println("-------");
        for (Map.Entry<String, String> entry : prefixMap.entrySet()) {
            System.out.println("Key: <" + entry.getKey() + ">, Value: <" + entry.getValue() + ">");
        }
        System.out.println("-------");
    }
}
