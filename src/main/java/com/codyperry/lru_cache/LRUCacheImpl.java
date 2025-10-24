package com.codyperry.lru_cache;

public class LRUCacheImpl<T, U> implements LRUCache<T, U> {
    /**
     * Put an item of type U in key of type T. This will evict the least recently used item if at capacity. Calling `put`
     * on an existing key will shift this item to the most recently used.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(T key, U value) {

    }

    /**
     * Remove the item at key and return it.
     *
     * @param key
     * @return The item if it exists, null otherwise.
     */
    @Override
    public U remove(T key) {
        return null;
    }

    /**
     * Get the item with the given key. This will shift this item to the most recently used.
     *
     * @param key
     * @return The item if it exists, null otherwise.
     */
    @Override
    public U get(T key) {
        return null;
    }
}
