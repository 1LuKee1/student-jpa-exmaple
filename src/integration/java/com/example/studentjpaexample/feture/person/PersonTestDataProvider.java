package com.example.studentjpaexample.feture.person;

import com.example.studentjpaexmaple.domain.person.Person;

import java.time.LocalDate;
import java.util.List;

interface PersonTestDataProvider {

    default List<Person> prepareMockData() {
        return List.of(
                Person.builder()
                        .firstName("Ania")
                        .lastName("Anita")
                        .birthDate(LocalDate.of(2010, 1, 1))
                        .build(),
                Person.builder()
                        .firstName("Jan")
                        .lastName("Kowalski")
                        .birthDate(LocalDate.of(2009, 1, 1))
                        .build(),
                Person.builder()
                        .firstName("Piotr")
                        .lastName("Nowak")
                        .birthDate(LocalDate.of(2008, 1, 1))
                        .build());
    }

    default List<Person> preparePersonListResponse() {
        return List.of(
                Person.builder()
                        .firstName("Ania")
                        .lastName("Anita")
                        .birthDate(LocalDate.of(2010, 1, 1))
                        .build(),
                Person.builder()
                        .firstName("Jan")
                        .lastName("Kowalski")
                        .birthDate(LocalDate.of(2009, 1, 1))
                        .build(),
                Person.builder()
                        .firstName("Piotr")
                        .lastName("Nowak")
                        .birthDate(LocalDate.of(2008, 1, 1))
                        .build());
    }
}
