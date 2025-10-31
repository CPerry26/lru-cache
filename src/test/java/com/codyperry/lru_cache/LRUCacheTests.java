package com.codyperry.lru_cache;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LRUCacheTests {
    @Test
    @Order(1)
    void testPutHappyPath() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(1);
        cache.put("1", 1);
        assertEquals(cache.get("1"), 1);
    }

    @Test
    @Order(2)
    void testRemoveHappyPath() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(1);
        cache.put("1", 1);
        Integer removed = cache.remove("1");
        assertEquals(removed, 1);
    }

    @Test
    @Order(3)
    void testGetHappyPath() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(2);
        cache.put("1", 1);
        cache.put("2", 2);
        assertEquals(cache.get("1"), 1);
    }

    @Test
    @Order(4)
    void testPutExistingKey() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(1);
        cache.put("1", 1);
        cache.put("1", 2);
        assertEquals(cache.get("1"), 2);
    }

    @Test
    @Order(5)
    void testPutAtCapacity() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(1);
        cache.put("1", 1);
        cache.put("2", 2);
        assertEquals(cache.get("1"), null);
        assertEquals(cache.get("2"), 2);
    }

    @Test
    @Order(6)
    void testRemoveNotInCache() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(1);
        Integer removed = cache.remove("1");
        assertEquals(removed, null);
    }

    @Test
    @Order(7)
    void testGetNotInCache() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(1);
        Integer entry = cache.get("1");
        assertEquals(entry, null);
    }

    @Test
    @Order(8)
    void testRUPutOrder() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(2);
        cache.put("1", 1);
        cache.put("2", 2);

        List<String> ruItems = cache.traverseRecentlyUsed();

        int value = 2;
        for (String string : ruItems) {
            assertEquals(string.contains(String.valueOf(value)), true);
            value--;
        }
    }

    @Test
    @Order(9)
    void testRUGetOrder() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(2);
        cache.put("1", 1);
        cache.put("2", 2);

        Integer item = cache.get("1");
        assertEquals(item, 1);

        List<String> ruItems = cache.traverseRecentlyUsed();

        int value = 1;
        for (String string : ruItems) {
            assertEquals(string.contains(String.valueOf(value)), true);
            value++;
        }
    }

    @Test
    @Order(10)
    void testRURemoveOrder() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(2);
        cache.put("1", 1);
        cache.put("2", 2);

        Integer removed = cache.remove("2");
        assertEquals(removed, 2);

        List<String> ruItems = cache.traverseRecentlyUsed();

        assertEquals(ruItems.size(), 1);
        assertEquals(ruItems.get(0).contains("1"), true);
    }

    @Test
    @Order(11)
    void testRUGetMiddleOrder() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(3);
        cache.put("1", 1);
        cache.put("2", 2);
        cache.put("3", 3);

        Integer get = cache.get("2");
        assertEquals(get, 2);

        List<String> ruItems = cache.traverseRecentlyUsed();
        assertEquals(ruItems.size(), 3);

        assertEquals(ruItems.get(0).contains("2"), true);
        assertEquals(ruItems.get(1).contains("3"), true);
        assertEquals(ruItems.get(2).contains("1"), true);
    }

    @Test
    @Order(12)
    void testRURemoveMiddleOrder() {
        final LRUCacheImpl<String, Integer> cache = new LRUCacheImpl<>(3);
        cache.put("1", 1);
        cache.put("2", 2);
        cache.put("3", 3);

        Integer get = cache.remove("2");
        assertEquals(get, 2);

        List<String> ruItems = cache.traverseRecentlyUsed();
        assertEquals(ruItems.size(), 2);

        assertEquals(ruItems.get(0).contains("3"), true);
        assertEquals(ruItems.get(1).contains("1"), true);
    }
}
