package com.zemingo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.zemingo.TestData.*;
import static org.junit.jupiter.api.Assertions.*;


public class QuickPopDataStructureTest{

    QuickPopDataStructure<Integer> quickPopInteger;
    QuickPopDataStructure<Person> quickPopPerson;
    QuickPopDataStructure<String> quickPopString;


    @BeforeEach
    public void setUp() throws Exception {
        quickPopInteger = new QuickPopDataStructure<>();
        quickPopPerson = new QuickPopDataStructure<>();
        quickPopString = new QuickPopDataStructure<>();

    }

    // Integer
    @Test
    @DisplayName("Push one element and pop should return the same element")
    public void testPushAndPopSingleElementInteger() {
        quickPopInteger.push(10);
        Integer result = quickPopInteger.pop();
        assertNotNull(result);
        assertEquals(Integer.valueOf(10), result);
    }

   @Test
   @DisplayName("Push multiple integers and pop them back in descending order")
    void   testPushAndPopMultipleElementIntegerDescendingOrder() {
        quickPopInteger.push(5);
        quickPopInteger.push(10);
        quickPopInteger.push(20);

        assertEquals(Integer.valueOf(20), quickPopInteger.pop());
        assertEquals(Integer.valueOf(10), quickPopInteger.pop());
        assertEquals(Integer.valueOf(5), quickPopInteger.pop());
    }

    @Test
    @DisplayName("Popping from an empty data structure should throw an exception")
    void testPopEmptyThrowsException() {
        assertThrows(IllegalStateException.class, () -> quickPopInteger.pop());
    }

    @Test
    @DisplayName("Push multiple integers with duplicates and pop them back in descending order")
    void testPushElementWithDuplicatesInteger() {
        quickPopInteger.push(5);
        quickPopInteger.push(10);
        quickPopInteger.push(10);

        assertEquals(Integer.valueOf(10), quickPopInteger.pop());
        assertEquals(Integer.valueOf(10), quickPopInteger.pop());
        assertEquals(Integer.valueOf(5), quickPopInteger.pop());
    }

    @Test
    @DisplayName("Mixing push and pop still preserves descending order")
    void testPushAndPopMixedOrderInteger(){
        quickPopInteger.push(5);
        quickPopInteger.push(10);
        assertEquals(Integer.valueOf(10), quickPopInteger.pop());

        quickPopInteger.push(20);
        assertEquals(Integer.valueOf(20), quickPopInteger.pop());
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
        quickPopPerson.push(PERSON_1);
        Person result = quickPopPerson.pop();
        assertNotNull(result);
        assertEquals(PERSON_1, result);
    }

    @Test
    @DisplayName("Push multiple Person objects and pop them back in descending order by age, then alphabetical by last and first name")
    void   testPushAndPopMultipleElementPeopleDescendingOrder() {
        quickPopPerson.push(PERSON_1);
        quickPopPerson.push(PERSON_2);
        quickPopPerson.push(PERSON_3);
        quickPopPerson.push(PERSON_4);

        assertEquals(PERSON_4,quickPopPerson.pop());
        assertEquals(PERSON_3,quickPopPerson.pop());
        assertEquals(PERSON_2,quickPopPerson.pop());
        assertEquals(PERSON_1, quickPopPerson.pop());
    }


    @Test
    @DisplayName("Popping from an empty data structure should throw an exception")
    void testPopEmptyThrowsExceptionPerson() {
        assertThrows(IllegalStateException.class, () -> quickPopPerson.pop());
    }

    @Test
    @DisplayName("Push multiple Person objects with duplicates and pop them back in descending order")
    void testPushElementWithDuplicatesPerson() {
        quickPopPerson.push(PERSON_1);
        quickPopPerson.push(PERSON_2);
        quickPopPerson.push(PERSON_3);
        quickPopPerson.push(PERSON_4);
        quickPopPerson.push(PERSON_4);

        assertEquals(PERSON_4,quickPopPerson.pop());
        assertEquals(PERSON_4,quickPopPerson.pop());
        assertEquals(PERSON_3,quickPopPerson.pop());
        assertEquals(PERSON_2,quickPopPerson.pop());
        assertEquals(PERSON_1, quickPopPerson.pop());
    }

    @Test
    @DisplayName("Mixing push and pop still preserves descending order")
    void testPushAndPopMixedOrderPerson(){
        quickPopPerson.push(PERSON_4);
        quickPopPerson.push(PERSON_2);
        assertEquals(PERSON_4, quickPopPerson.pop());

        quickPopPerson.push(PERSON_3);
        assertEquals(PERSON_3, quickPopPerson.pop());
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
        quickPopString.push(STR1);
        var result = quickPopString.pop();
        assertNotNull(result);
        assertEquals(STR1, result);
    }

    @Test
    @DisplayName("Push multiple String and pop them back")
    void testPushAndPopMultipleElementStringDescendingOrder() {
        quickPopString.push(STR1);
        quickPopString.push(STR2);
        quickPopString.push(STR3);

        assertEquals(STR3,quickPopString.pop());
        assertEquals(STR2,quickPopString.pop());
        assertEquals(STR1, quickPopString.pop());
    }


    @Test
    @DisplayName("Popping from an empty data structure should throw an exception")
    void testPopEmptyThrowsExceptionString() {
        assertThrows(IllegalStateException.class, () -> quickPopString.pop());
    }

    @Test
    @DisplayName("Push multiple Strings with duplicates and pop them descending alphabetical order")
    void testPushElementWithDuplicatesString() {
        quickPopString.push(STR1);
        quickPopString.push(STR1);
        quickPopString.push(STR2);
        quickPopString.push(STR3);

        assertEquals(STR3,quickPopString.pop());
        assertEquals(STR2,quickPopString.pop());
        assertEquals(STR1, quickPopString.pop());
        assertEquals(STR1, quickPopString.pop());
    }

    @Test
    @DisplayName("Mixing push and pop still preserves descending alphabetical order")
    void testPushAndPopMixedOrderString(){
        quickPopString.push(STR1);
        quickPopString.push(STR2);
        assertEquals(STR2, quickPopString.pop());

        quickPopString.push(STR3);
        assertEquals(STR3, quickPopString.pop());
    }

    @Test
    @DisplayName("Pop String should run in constant time regardless of structure size")
    void popStringShouldBeConstantTime() {
        int n = 1_000_000;
        for (int i = 0; i < n; i++) {
            quickPopString.push(STR1);
        }
        long start = System.nanoTime();
        quickPopString.pop();
        long duration = System.nanoTime() - start;
        assertTrue(duration < 1_000_000, "Pop took too long: " + duration + " ns");
    }

}