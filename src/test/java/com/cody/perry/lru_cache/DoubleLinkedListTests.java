package com.cody.perry.lru_cache;

import com.codyperry.lru_cache.model.DoubleLinkedList;
import com.codyperry.lru_cache.model.Node;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoubleLinkedListTests {
    @Test
    @Order(1)
    void testInsertionHappyPath() {
        final DoubleLinkedList<String, Integer> linkedList = new DoubleLinkedList<>();

        Node<String, Integer> node1 = new Node<>("1", 1);
        Node<String, Integer> node2 = new Node<>("2", 2);
        Node<String, Integer> node3 = new Node<>("3", 3);

        linkedList.setAsHead(node1);
        linkedList.setAsHead(node2);
        linkedList.setAsHead(node3);
    }

    @Test
    @Order(2)
    void testTraverseForwards() {
        final DoubleLinkedList<String, Integer> linkedList = new DoubleLinkedList<>();

        Node<String, Integer> node1 = new Node<>("1", 1);
        Node<String, Integer> node2 = new Node<>("2", 2);
        Node<String, Integer> node3 = new Node<>("3", 3);

        linkedList.setAsHead(node1);
        linkedList.setAsHead(node2);
        linkedList.setAsHead(node3);

        List<String> forwards = linkedList.buildStringsForward();
        assertEquals(forwards.size(), 3);

        int value = 3;
        for (String string : forwards) {
            assertEquals(string.contains(String.valueOf(value)), true);
            value--;
        }
    }

    @Test
    @Order(3)
    void testTraverseBackwards() {
        final DoubleLinkedList<String, Integer> linkedList = new DoubleLinkedList<>();

        Node<String, Integer> node1 = new Node<>("1", 1);
        Node<String, Integer> node2 = new Node<>("2", 2);
        Node<String, Integer> node3 = new Node<>("3", 3);

        linkedList.setAsHead(node1);
        linkedList.setAsHead(node2);
        linkedList.setAsHead(node3);

        List<String> backwards = linkedList.buildStringsReverse();
        assertEquals(backwards.size(), 3);

        int value = 1;
        for (String string : backwards) {
            assertEquals(string.contains(String.valueOf(value)), true);
            value++;
        }
    }

    @Test
    @Order(4)
    void testRemoveTailEmptyList() {
        final DoubleLinkedList<String, Integer> linkedList = new DoubleLinkedList<>();
        Node<String, Integer> tail = linkedList.removeTail();
        assertEquals(tail == null, true);
    }

    @Test
    @Order(5)
    void testShiftToHead() {
        final DoubleLinkedList<String, Integer> linkedList = new DoubleLinkedList<>();
        Node<String, Integer> node1 = new Node<>("1", 1);
        Node<String, Integer> node2 = new Node<>("2", 2);
        linkedList.setAsHead(node1);
        linkedList.setAsHead(node2);
        linkedList.shiftToHead(node1);

        Node<String, Integer> tail = linkedList.removeTail();
        assertEquals(tail == node2, true);
    }
}
