package com.zemingo;

import java.util.concurrent.locks.ReentrantLock;

public class QuickPushDataStructure<T extends Comparable<T>> {
    private Node<T> head;
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Finds, removes, and returns the biggest value in the list.
     *
     * It goes through the list to find the maximum element, removes it from the list,
     * and returns it. If the list is empty, it throws an exception.
     *
     * Since we have to check every node, the time complexity is O(n).
     * The lock makes sure other threads don’t mess with the list while we’re popping.
     *
     * @return the largest item in the list
     * @throws IllegalStateException if the list is empty
     */


    public T pop() {
        lock.lock();
        try {
            if (head == null) {
                throw new IllegalStateException("head is null");
            }

            Node<T> max = head;
            Node<T> current = head;
            Node<T> prevMax = null;
            Node<T> prev = null;

            while (current != null) {
                if (current.getData().compareTo(max.getData()) > 0) {
                    max = current;
                    prevMax = prev;
                }
                prev = current;
                current = current.getNext();
            }
            if (prevMax == null) {
                head = head.getNext();
            } else {
                prevMax.setNext(max.getNext());
            }

            return max.getData();
        } finally {
            lock.unlock();
        }
    }



    /**
     * Adds a new value at the front of the list.
     *
     * Since we always insert at the head, this is super fast (O(1)).
     * The lock makes sure no other thread can mess with the list while we’re adding.
     *
     * @param value the item to add to the list
     */

    public void push(T value) {
        lock.lock();
        try {
            Node<T> node = new Node<>();
            node.setData(value);
            node.setNext(head);
            head = node;
        } finally {
            lock.unlock();
        }
    }

}
