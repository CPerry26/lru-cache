package com.codyperry.lru_cache;

import com.codyperry.lru_cache.model.DoubleLinkedList;
import com.codyperry.lru_cache.model.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LRUCacheImpl<T, U> implements LRUCache<T, U> {
    private Map<T, Node<T, U>> cacheEntries;
    private DoubleLinkedList<T, U> usedList;
    private int maxCapacity;

    public LRUCacheImpl(int capacity) {
        this.cacheEntries = new HashMap<>();
        this.usedList = new DoubleLinkedList<>();
        this.maxCapacity = capacity;
    }

    /**
     * Put an item of type U in key of type T. This will evict the least recently used item if at capacity. Calling `put`
     * on an existing key will shift this item to the most recently used.
     *
     * @param key
     * @param value
     */
    @Override
    public synchronized void put(T key, U value) {
        if (this.cacheEntries.containsKey(key)) {
            Node<T, U> prevEntry = this.cacheEntries.get(key);
            Node<T, U> newEntry = new Node<>(key, value);

            // Update our map entry, remove the old entry from the list and add the new node.
            this.cacheEntries.put(key, newEntry);
            this.usedList.remove(prevEntry);
            this.usedList.setAsHead(newEntry);
        } else {
            Node<T, U> newEntry = new Node<>(key, value);

            // Add a new entry and set it directly as the head. If we're at max capacity, evict the LRU.
            if (this.cacheEntries.size() == maxCapacity) {
                Node<T, U> tail = this.usedList.removeTail();
                this.cacheEntries.remove(tail.getKey());
            }

            this.cacheEntries.put(key, newEntry);
            this.usedList.setAsHead(newEntry);
        }
    }

    /**
     * Remove the item at key and return it.
     *
     * @param key
     * @return The item if it exists, null otherwise.
     */
    @Override
    public synchronized U remove(T key) {
        if (!this.cacheEntries.containsKey(key)) {
            return null;
        }

        // Remove our map entry and the linked list reference.
        Node<T, U> entry = this.cacheEntries.remove(key);
        this.usedList.remove(entry);

        return entry.getValue();
    }

    /**
     * Get the item with the given key. This will shift this item to the most recently used.
     *
     * @param key
     * @return The item if it exists, null otherwise.
     */
    @Override
    public synchronized U get(T key) {
        if (!this.cacheEntries.containsKey(key)) {
            return null;
        }

        // Shift the entry to the most recently used (the head).
        Node<T, U> entry = this.cacheEntries.get(key);
        this.usedList.shiftToHead(entry);

        return entry.getValue();
    }

    /**
     * A utility method only for testing to traverse the most recently used to least recently used for validating functionality.
     *
     * @return Entries for the internal linked list.
     */
    protected synchronized List<String> traverseRecentlyUsed() {
        return this.usedList.buildStringsForward();
    }
}
