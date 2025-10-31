package com.codyperry.lru_cache.model;

import java.util.ArrayList;
import java.util.List;

public final class DoubleLinkedList<T, U> {
    private Node<T, U> sentinelHead;
    private Node<T, U> sentinelTail;

    public DoubleLinkedList() {
        // Doing this without sentinel nodes is painful, so I'm just using them. I got too lost in the sauce.
        this.sentinelHead = new Node<>(null, null);
        this.sentinelTail = new Node<>(null, null);

        this.sentinelHead.setNext(this.sentinelTail);
        this.sentinelTail.setPrevious(this.sentinelHead);
    }

    /**
     * Shift the given node to the front of the list. This removes any previous
     * reference of the incoming node and shifts accordingly.
     *
     * @param node
     */
    public void shiftToHead(Node<T, U> node) {
        remove(node);
        setAsHead(node);
    }

    /**
     * Set the given node as the head. This points the sentinel head at the node passed in.
     *
     * @param node
     */
    public void setAsHead(Node<T, U> node) {
        // Set the node being inserted to be after the head.
        node.setPrevious(this.sentinelHead);
        node.setNext(this.sentinelHead.getNext());

        // Set the old next to head to point to the node.
        this.sentinelHead.getNext().setPrevious(node);
        this.sentinelHead.setNext(node);
    }

    /**
     * Remove the specified node. This will shift the list accordingly.
     *
     * @param node
     *
     * @return The node that was removed.
     */
    public Node<T, U> remove(Node<T, U> node) {
        Node<T, U> prev = node.getPrevious();
        Node<T, U> next = node.getNext();

        // Shift the elements around the node being removed.
        prev.setNext(next);
        next.setPrevious(prev);

        return node;
    }

    /**
     * Remove the tail node in the list. If there's no elements in the list, return null.
     *
     * @return Return the previous tail element that was removed.
     */
    public Node<T, U> removeTail() {
        // Check if we only have a sentinel head and tail.
        if (this.sentinelHead.getNext() == this.sentinelTail) {
            return null;
        }

        Node<T, U> tail = this.sentinelTail.getPrevious();
        remove(tail);

        return tail;
    }

    /**
     * Method to traverse the linked list forward and create strings for each item.
     *
     * @return
     */
    public List<String> buildStringsForward() {
        List<String> strings = new ArrayList<>();

        Node<T, U> currNode = this.sentinelHead.getNext();

        while (currNode != null && currNode != this.sentinelTail) {
            StringBuilder builder = new StringBuilder();

            builder.append(currNode.getKey());
            builder.append(",");
            builder.append(currNode.getValue());

            strings.add(builder.toString());

            currNode = currNode.getNext();
        }

        return strings;
    }

    /**
     * Method to traverse the linked list in reverse and create strings for each item.
     *
     * @return
     */
    public List<String> buildStringsReverse() {
        List<String> strings = new ArrayList<>();

        Node<T, U> currNode = this.sentinelTail.getPrevious();

        while (currNode != null && currNode != this.sentinelHead) {
            StringBuilder builder = new StringBuilder();

            builder.append(currNode.getKey());
            builder.append(",");
            builder.append(currNode.getValue());

            strings.add(builder.toString());

            currNode = currNode.getPrevious();
        }

        return strings;
    }
}
