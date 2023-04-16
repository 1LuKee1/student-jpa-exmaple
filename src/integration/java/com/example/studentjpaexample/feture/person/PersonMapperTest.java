package com.example.studentjpaexample.feture.person;

import com.example.studentjpaexmaple.domain.address.Address;
import com.example.studentjpaexmaple.domain.address.AddressMapper;
import com.example.studentjpaexmaple.domain.address.AddressMapperImpl;
import com.example.studentjpaexmaple.domain.person.Person;
import com.example.studentjpaexmaple.domain.person.PersonMapper;
import com.example.studentjpaexmaple.domain.person.PersonMapperImpl;
import com.example.studentjpaexmaple.domain.person.dto.PersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@SpringBootTest(classes = {PersonMapperImpl.class, AddressMapperImpl.class})
class PersonMapperTest {

    @Autowired
    PersonMapper personMapper;

    @Autowired
    AddressMapper addressMapper;

    @Test
    void should_map_student_to_studentDto_when_params_are_correct() {
        //given
        Person person = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());

        //when
        PersonDto personDto = personMapper.mapToPersonDto(person);

        //then
        assertAll(
                () -> assertThat(personDto.getId()).isEqualTo(person.getId()),
                () -> assertThat(personDto.getFirstName()).isEqualTo(person.getFirstName()),
                () -> assertThat(personDto.getLastName()).isEqualTo(person.getLastName()),
                () -> assertThat(personDto.getBirthDate()).isEqualTo(person.getBirthDate()),
                () -> assertThat(personDto.getDefaultAddress()).isNull(),
                () -> assertThat(personDto.getAddresses()).isEmpty()

        );
    }

    @Test
    void should_map_student_to_studentDto_and_set_default_address_to_defaultAddress_field() {
        //given
        Address address1 = new Address(UUID.randomUUID(), "someStreetname", "6423-13212", "102b", "2", true);
        Address address2 = new Address(UUID.randomUUID(), "someStreetname1", "6423-13212", "103b", "2", false);
        List<Address> addresses = List.of(address1, address2);
        Person person = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2000, 2, 17), addresses);


        //when
        PersonDto personDto = personMapper.mapToPersonDto(person);

        //then
        assertAll(
                () -> assertThat(personDto.getId()).isEqualTo(person.getId()),
                () -> assertThat(personDto.getFirstName()).isEqualTo(person.getFirstName()),
                () -> assertThat(personDto.getLastName()).isEqualTo(person.getLastName()),
                () -> assertThat(personDto.getBirthDate()).isEqualTo(person.getBirthDate()),
                () -> assertThat(personDto.getDefaultAddress()).isEqualTo(addressMapper.maptoAddressDto(address1)),
                () -> assertThat(personDto.getAddresses()).containsExactlyInAnyOrder(addressMapper.maptoAddressDto(address2)).hasSize(1)

        );
    }
}