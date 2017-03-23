package com.robertsmieja.example.apache.commons.collections4;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.SortedMap;

public class TrieExamples {
    Trie<String, String> trie;

    @Before
    public void setup(){
        trie = new PatriciaTrie<>();

        trie.put("Awesome", "value");
        trie.put("Awe", "some");
        trie.put("awesome", "lowercase value");
        trie.put("different", "keyValuePair");
        trie.put("Awesome Key", "with an Awesome Value");
        trie.put("A", "B");
    }

    @Test
    public void triePrefixMap(){
        SortedMap<String, String> prefixMap = trie.prefixMap("A");
        printPrefixMap(prefixMap);

        prefixMap = trie.prefixMap("Awe");
        printPrefixMap(prefixMap);
    }

    private void printPrefixMap(SortedMap<String, String> prefixMap){
        System.out.println("-------");
        for (Map.Entry<String, String> entry : prefixMap.entrySet()) {
            System.out.println("Key: <" + entry.getKey() + ">, Value: <" + entry.getValue() + ">");
        }
        System.out.println("-------");
    }
}
