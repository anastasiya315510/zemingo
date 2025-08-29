package com.zemingo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class QuickPopDataStructureConcurrencyTest {

    private QuickPopDataStructure<Integer> structure;

    @BeforeEach
    void setUp() {
        structure = new QuickPopDataStructure<>();
    }

    @Test
    void concurrentPushAndPop_shouldRemainConsistent() throws InterruptedException {
        int numThreads = 5;
        int numElements = 1000;

        CountDownLatch latch = new CountDownLatch(numThreads * 2);
        AtomicInteger popCount = new AtomicInteger();

        for (int t = 0; t < numThreads; t++) { // Pusher threads
            final int base = t * numElements;
            new Thread(() -> {
                for (int i = 0; i < numElements; i++) {
                    structure.push(base + i);
                }
                latch.countDown();
            }).start();
        }

        for (int t = 0; t < numThreads; t++) { // Popper threads
            new Thread(() -> {
                int localCount = 0;
                while (localCount < numElements) {
                    try {
                        structure.pop();
                        localCount++;
                        popCount.incrementAndGet();
                    } catch (IllegalStateException ignored) {

                    }
                }
                latch.countDown();
            }).start();
        }

        latch.await();

        assertEquals(numThreads * numElements, popCount.get());
    }
}
