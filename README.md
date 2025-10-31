# LRU Cache

An attempt at implementing a thread-safe and fast implementation of an LRU cache based on a UML diagram.

This was built with Java 17 and uses Maven. All tests are executable and passing using JUnit.

## Implementation Considerations

In order to speed up this cache as fast as possible, there are two data structures. Maps allow O(1) lookup for keys, and the items are tracked for access using a linked list. Doubly linked lists allow for constant time insertion operations, keeping things at O(1).

### Capacity

Each cache is instantiated with a max capacity. If max capacity is reached AND we are adding a new item, the least recently used (tail) is evicted.

### Operations

Operations on the cache should shift the linked list in some way. For example, a get moves that item to the head.

### Sentinels

Traversing and working with linked lists are much easier with sentinel values (dummy head/tail), so the underlying linked list implementation uses them.

## Concurrency

I tried to implement this with concurrency in mind. However, I think there are important distinctions to make here.

* Generics
  * I was originally going to use singletons to enforce only ever a single instance. I couldn't get that to work with generics, so I instead tried to use visibility modifiers to control access and only allowing the cache as an entrypoint.
* ConcurrentHashMap vs Synchronized
  * Concurrent hash maps can allow a lot of readers or writers using buckets. Because I'm using an underlying linked list as well, the concurrent hash map could allow concurrent writes to the linked list, causing dirty state. To keep the implementation simple, I synchronized the outer method blocks. A more in depth implementation should synchronize both the hash map AND the linked list since it's actively modified from the map operations.
* Linked list
  * Since the linked list should only be called from a synchronized public method, I chose not to make those synchronized. Similar to the above, that should be taken into account in a more complete implementation.

## Future

In the future, the goal is to implement TTL support with automatic eviction using scheduled threads and a scheduled executor.