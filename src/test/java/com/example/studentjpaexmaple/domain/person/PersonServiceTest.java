package com.example.studentjpaexmaple.domain.person;

import com.example.studentjpaexmaple.domain.address.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    private final PersonRepository personRepository = mock(PersonRepository.class);
    private final TimeProvider timeProvider = mock(TimeProvider.class);
    private final PersonValidator validator = new PersonValidator(timeProvider);
    private final PersonService personService = new PersonService(personRepository, validator);

    @BeforeEach
    void setUp() {
        given(timeProvider.getActualDate()).willReturn(LocalDate.now());
    }

    @Test
    void should_create_student() {
        //given
        Person person = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());
        UUID personId = person.getId();
        given(personRepository.saveAndFlush(person)).willReturn(person);
        given(personRepository.findAll()).willReturn(List.of(
                new Person(personId, "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList())
        ));

        //when

        Person savedPerson = personService.createPerson(person);
        List<Person> allPersons = personService.getAllPersons();

        //then
        assertAll(
                () -> assertThat(allPersons).hasSize(1),
                () -> assertThat(allPersons).containsExactlyInAnyOrder(savedPerson)
        );
    }

    @Test
    void should_return_all_available_students() {
        //given
        Person person1 = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());
        Person person2 = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());
        given(personRepository.findAll()).willReturn(List.of(person1, person2));

        //when
        List<Person> allPersons = personService.getAllPersons();

        //then
        assertAll(
                () -> assertThat(allPersons).hasSize(2),
                () -> assertThat(allPersons).containsExactlyInAnyOrder(person1, person2)
        );
    }

    @Test
    void should_find_student_by_given_id() {
        //given
        UUID personId = UUID.randomUUID();
        Person person = new Person(personId, "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());
        given(personRepository.findById(personId)).willReturn(Optional.of(person));

        //when
        Person personById = personService.findPersonById(personId);

        //then
        assertAll(
                () -> assertEquals(personById.getId(), person.getId()),
                () -> assertEquals(personById.getFirstName(), person.getFirstName()),
                () -> assertEquals(personById.getLastName(), person.getLastName()),
                () -> assertEquals(personById.getBirthDate(), person.getBirthDate()),
                () -> assertThat(personById.getAddresses()).isEmpty()
        );
    }

    @Test
    void should_find_student_by_name_when_student_exist() {
        //given
        String firstName = "John";
        Person person = new Person(UUID.randomUUID(), firstName, "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());
        given(personRepository.existsByFirstName(firstName)).willReturn(true);
        given(personRepository.findPersonByFirstName(firstName)).willReturn(Optional.of(person));

        //when
        Person personByFirstName = personService.findPersonByFirstName(firstName);

        //then
        assertAll(
                () -> assertThat(personByFirstName).isEqualTo(person),
                () -> assertThat(personByFirstName.getFirstName()).isEqualTo(person.getFirstName())
        );
    }

    @Test
    void should_delete_student_when_id_is_valid() {
        //given
        UUID personId = UUID.randomUUID();
        Person person = new Person(personId, "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());
        given(personRepository.findById(personId)).willReturn(Optional.of(person));

        //when
        personService.deletePersonById(personId);

        //then
        verify(personRepository, times(1)).deleteById(personId);
    }

    @Test
    void getAllAdultsStudent() {
        //given
        Person person1 = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());
        Person person2 = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());
        Person person3 = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2010, 2, 17), Collections.emptyList());
        given(personRepository.findAll()).willReturn(List.of(person1, person2, person3));

        //when
        List<Person> allAdultsPersons = personService.getAllAdultsPersons();

        //then
        assertAll(
                () -> assertThat(allAdultsPersons).hasSize(2),
                () -> assertThat(allAdultsPersons).containsExactlyInAnyOrder(person1, person2)
        );
    }

    @Test
    void should_update_person_fields_when_person_with_searched_id_exist() {
        //given
        Person person1 = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());
        Person person2 = new Person("Luke", "Skywalker", LocalDate.of(2010, 12, 17), Collections.emptyList());

        given(personRepository.findById(person1.getId())).willReturn(Optional.of(person1));
        given(personRepository.saveAndFlush(person1)).willReturn(person2);

        //when
        Person updatedPerson = personService.updatePerson(person1.getId(), person2);

        //then
        assertAll(
                () -> assertThat(updatedPerson.getFirstName()).isEqualTo("Luke"),
                () -> assertThat(updatedPerson.getLastName()).isEqualTo("Skywalker"),
                () -> assertThat(updatedPerson.getBirthDate()).isEqualTo(LocalDate.of(2010, 12, 17)),
                () -> assertThat(updatedPerson.getAddresses()).isEqualTo(Collections.emptyList())
        );
    }

    @Test
    void should_return_all_addresses_belong_to_student_by_given_id_when_id_is_present() {
        // given
        Address address1 = new Address(UUID.randomUUID(), "someStreetname1", "6423-13212", "102b", "2", true);
        Address address2 = new Address(UUID.randomUUID(), "someStreetname2", "6423-13212", "102b", "2", false);
        UUID personId = UUID.randomUUID();
        Person person = new Person(personId, "John", "Doe", LocalDate.of(2000, 2, 17), List.of(address1, address2));
        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        // when
        List<Address> allPersonAddressesByPersonId = personService.getAddressesByPersonId(personId);

        // then
        assertAll(
                () -> assertThat(allPersonAddressesByPersonId).hasSize(2).containsExactlyInAnyOrder(address1, address2)
        );
    }
}   