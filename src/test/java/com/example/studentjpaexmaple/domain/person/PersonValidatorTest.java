package com.example.studentjpaexmaple.domain.person;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonValidatorTest {

    private final TimeProvider timeProvider = new TimeProvider();
    private final PersonValidator validator = new PersonValidator(timeProvider);

    @Test
    void should_return_true_when_student_is_adult() {
        //given
        Person person = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());

        //when
        boolean isStudentAdult = validator.isPersonAdult(person);

        //then
        assertTrue(isStudentAdult);
    }

    @Test
    void should_return_false_when_student_is_not_adult() {
        //given
        Person person = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2010, 2, 17), Collections.emptyList());

        //when
        boolean isStudentAdult = validator.isPersonAdult(person);

        //then
        assertFalse(isStudentAdult);
    }
}