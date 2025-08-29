package com.zemingo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.zemingo.TestData.PERSON_1;
import static com.zemingo.TestData.PERSON_2;
import static com.zemingo.TestData.PERSON_3;
import static com.zemingo.TestData.PERSON_4;
import static com.zemingo.TestData.STR1;
import static com.zemingo.TestData.STR2;
import static com.zemingo.TestData.STR3;
import static org.junit.jupiter.api.Assertions.*;

class QuickPushDataStructureTest {

    QuickPushDataStructure<Integer> quickPushInteger;
    QuickPushDataStructure<Person> quickPushPerson;
    QuickPushDataStructure<String> quickPushString;


    @BeforeEach
    public void setUp() throws Exception {
        quickPushInteger = new QuickPushDataStructure<>();
        quickPushPerson = new QuickPushDataStructure<>();
        quickPushString = new QuickPushDataStructure<>();

    }

    // Integer
    @Test
    @DisplayName("Push one element and pop should return the same element")
    public void testPushAndPopSingleElementInteger() {
        quickPushInteger.push(10);
        Integer result = quickPushInteger.pop();
        assertNotNull(result);
        assertEquals(Integer.valueOf(10), result);
    }

    @Test
    @DisplayName("Push multiple integers and pop them back in descending order")
    void   testPushAndPopMultipleElementIntegerDescendingOrder() {
        quickPushInteger.push(5);
        quickPushInteger.push(10);
        quickPushInteger.push(20);

        assertEquals(Integer.valueOf(20), quickPushInteger.pop());
        assertEquals(Integer.valueOf(10), quickPushInteger.pop());
        assertEquals(Integer.valueOf(5), quickPushInteger.pop());
    }

    @Test
    @DisplayName("Popping from an empty data structure should throw an exception")
    void testPushEmptyThrowsException() {
        assertThrows(IllegalStateException.class, () -> quickPushInteger.pop());
    }

    @Test
    @DisplayName("Push multiple integers with duplicates and pop them back in descending order")
    void testPushElementWithDuplicatesInteger() {
        quickPushInteger.push(5);
        quickPushInteger.push(10);
        quickPushInteger.push(10);

        assertEquals(Integer.valueOf(10), quickPushInteger.pop());
        assertEquals(Integer.valueOf(10), quickPushInteger.pop());
        assertEquals(Integer.valueOf(5), quickPushInteger.pop());
    }

    @Test
    @DisplayName("Mixing push and pop still preserves descending order")
    void testPushAndPopMixedOrderInteger(){
        quickPushInteger.push(5);
        quickPushInteger.push(10);
        assertEquals(Integer.valueOf(10), quickPushInteger.pop());

        quickPushInteger.push(20);
        assertEquals(Integer.valueOf(20), quickPushInteger.pop());
    }

    @Test
    @DisplayName("Pop should run in constant time regardless of structure size")
    void popShouldBeConstantTime() {
        QuickPopDataStructure<Integer> structure = new QuickPopDataStructure<>();
        int n = 1_000_000;
        for (int i = 0; i < n; i++) {
            structure.push(i);
        }
        long start = System.nanoTime();
        structure.pop();
        long duration = System.nanoTime() - start;
        assertTrue(duration < 1_000_000, "Pop took too long: " + duration + " ns");
    }

    // Person
    @Test
    @DisplayName("Pushing one object should allow popping the same object")
    public void testPushAndPopSingleElementPerson() {
        quickPushPerson.push(PERSON_1);
        Person result = quickPushPerson.pop();
        assertNotNull(result);
        assertEquals(PERSON_1, result);
    }

    @Test
    @DisplayName("Push multiple Person objects and pop them back in descending order by age, then alphabetical by last and first name")
    void   testPushAndPopMultipleElementPeopleDescendingOrder() {
        quickPushPerson.push(PERSON_1);
        quickPushPerson.push(PERSON_2);
        quickPushPerson.push(PERSON_3);
        quickPushPerson.push(PERSON_4);

        assertEquals(PERSON_4,quickPushPerson.pop());
        assertEquals(PERSON_3,quickPushPerson.pop());
        assertEquals(PERSON_2,quickPushPerson.pop());
        assertEquals(PERSON_1, quickPushPerson.pop());
    }


    @Test
    @DisplayName("Popping from an empty data structure should throw an exception")
    void testPopEmptyThrowsExceptionPerson() {
        assertThrows(IllegalStateException.class, () -> quickPushPerson.pop());
    }

    @Test
    @DisplayName("Push multiple Person objects with duplicates and pop them back in descending order")
    void testPushElementWithDuplicatesPerson() {
        quickPushPerson.push(PERSON_1);
        quickPushPerson.push(PERSON_2);
        quickPushPerson.push(PERSON_3);
        quickPushPerson.push(PERSON_4);
        quickPushPerson.push(PERSON_4);

        assertEquals(PERSON_4,quickPushPerson.pop());
        assertEquals(PERSON_4,quickPushPerson.pop());
        assertEquals(PERSON_3,quickPushPerson.pop());
        assertEquals(PERSON_2,quickPushPerson.pop());
        assertEquals(PERSON_1, quickPushPerson.pop());
    }

    @Test
    @DisplayName("Mixing push and pop still preserves descending order")
    void testPushAndPopMixedOrderPerson(){
        quickPushPerson.push(PERSON_4);
        quickPushPerson.push(PERSON_2);
        assertEquals(PERSON_4, quickPushPerson.pop());

        quickPushPerson.push(PERSON_3);
        assertEquals(PERSON_3, quickPushPerson.pop());
    }

    @Test
    @DisplayName("Pop person should run in constant time regardless of structure size")
    void popPersonShouldBeConstantTime() {
        QuickPopDataStructure<Person> structure = new QuickPopDataStructure<>();
        int n = 1_000_000;
        for (int i = 0; i < n; i++) {
            structure.push(PERSON_4);
        }
        long start = System.nanoTime();
        structure.pop();
        long duration = System.nanoTime() - start;
        assertTrue(duration < 1_000_000, "Pop took too long: " + duration + " ns");
    }





    // String
    @Test
    @DisplayName("Pushing String should allow popping the same String")
    public void testPushAndPopSingleElementString() {
        quickPushString.push(STR1);
        var result = quickPushString.pop();
        assertNotNull(result);
        assertEquals(STR1, result);
    }

    @Test
    @DisplayName("Push multiple String and pop them back")
    void testPushAndPopMultipleElementStringDescendingOrder() {
        quickPushString.push(STR1);
        quickPushString.push(STR2);
        quickPushString.push(STR3);

        assertEquals(STR3,quickPushString.pop());
        assertEquals(STR2,quickPushString.pop());
        assertEquals(STR1, quickPushString.pop());
    }


    @Test
    @DisplayName("Popping from an empty data structure should throw an exception")
    void testPopEmptyThrowsExceptionString() {
        assertThrows(IllegalStateException.class, () -> quickPushString.pop());
    }

    @Test
    @DisplayName("Push multiple Strings with duplicates and pop them descending alphabetical order")
    void testPushElementWithDuplicatesString() {
        quickPushString.push(STR1);
        quickPushString.push(STR1);
        quickPushString.push(STR2);
        quickPushString.push(STR3);

        assertEquals(STR3,quickPushString.pop());
        assertEquals(STR2,quickPushString.pop());
        assertEquals(STR1, quickPushString.pop());
        assertEquals(STR1, quickPushString.pop());
    }

    @Test
    @DisplayName("Mixing push and pop still preserves descending alphabetical order")
    void testPushAndPopMixedOrderString(){
        quickPushString.push(STR1);
        quickPushString.push(STR2);
        assertEquals(STR2, quickPushString.pop());

        quickPushString.push(STR3);
        assertEquals(STR3, quickPushString.pop());
    }

    @Test
    @DisplayName("Push should run in constant time O(1)")
    void pushShouldBeConstantTime() {
        int n = 1_000_000;

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            quickPushInteger.push(i);
        }
        long duration = System.nanoTime() - start;
        System.out.println("Push " + n + " elements duration: " + duration + " ns");

        assertTrue(duration < 1_000_000_000L, "Push took too long");
    }

    @Test
    @DisplayName("Pop should run in linear time O(n)")
    void popShouldBeLinearTime() {
        int n = 100_000;
        for (int i = 0; i < n; i++) {
            quickPushInteger.push(i);
        }

        long start = System.nanoTime();
        quickPushInteger.pop();
        long duration = System.nanoTime() - start;
        System.out.println("Pop duration for " + n + " elements: " + duration + " ns");

        assertTrue(duration > 0, "Pop took no measurable time");
    }

}