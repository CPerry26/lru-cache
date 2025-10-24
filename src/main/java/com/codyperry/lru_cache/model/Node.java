package com.codyperry.lru_cache.model;

public class Node<T, U> {
    private T key;
    private U value;

    private Node<T, U> next;
    private Node<T, U> previous;

    public Node(T key, U value) {
        this.key = key;
        this.value = value;

        this.next = null;
        this.previous = null;
    }

    public T getKey() {
        return key;
    }

    public U getValue() {
        return value;
    }

    public Node<T, U> getNext() {
        return next;
    }

    public void setNext(Node<T, U> next) {
        this.next = next;
    }

    public Node<T, U> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T, U> previous) {
        this.previous = previous;
    }
}
