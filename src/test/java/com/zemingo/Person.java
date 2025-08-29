package com.zemingo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Person implements  Comparable<Person> {

    private String firstName;
    private String lastName;
    private int age;


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person other = (Person) obj;
            return other.age == age && other.firstName.equals(firstName) && other.lastName.equals(lastName);
        }
        return false;

    }

    @Override
    public int compareTo(Person o) {
        int ageCompare = Integer.compare(this.age, o.age);
        if (ageCompare != 0) return ageCompare;

        int lastNameCompare = - this.lastName.compareTo(o.lastName);
        if (lastNameCompare != 0) return lastNameCompare;

        return - this.firstName.compareTo(o.firstName);
    }
}
