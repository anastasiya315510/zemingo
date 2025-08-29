package com.zemingo;

import java.util.concurrent.locks.ReentrantLock;

public class QuickPopDataStructure<T extends Comparable<T>> {

    private Node<T> head;
    private final ReentrantLock lock = new ReentrantLock();


    /**
     * Removes and returns the maximum element from the data structure.
     *
     * <p>Time complexity: O(1)</p>
     *
     * @return the maximum element currently in the data structure
     * @throws IllegalStateException if the data structure is empty
     */
    public T pop() {
        lock.lock();
        try {
            if (head != null) {
                T max = head.getData();
                head = head.getNext();
                return max;
            }
            throw new IllegalStateException("head is null");
        } finally {
            lock.unlock();
        }
    }




    /**
     * Adds a new value to the list in the right spot so that the biggest value is always at the front.
     *
     * If the list is empty or the new value is bigger than the head, it just becomes the new head.
     * Otherwise, it goes through the list to find where it should go to keep everything in descending order.
     *
     * <p>Time complexity: O(n)</p>
     *
     * The lock makes sure that multiple threads can’t mess up the list while we’re inserting.
     *
     * @param value the item to add; it needs to be comparable so we know which is bigger
     */

    public void push(T value) {
        lock.lock();
        try {
            Node<T> node = new Node<>();
            node.setData(value);

            if (head == null || value.compareTo(head.getData()) > 0) {
                node.setNext(head);
                head = node;
                return;
            } else {
                Node<T> current = head;
                while (current.getNext() != null && current.getData().compareTo(value) > 0) {
                    current = current.getNext();
                }
                node.setNext(current.getNext());
                current.setNext(node);
            }
        } finally {
            lock.unlock();
        }

    }

}
