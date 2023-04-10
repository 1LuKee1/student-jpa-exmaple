package com.example.studentjpaexample.mapper;

import com.example.studentjpaexmaple.domain.address.Address;
import com.example.studentjpaexmaple.domain.address.AddressMapper;
import com.example.studentjpaexmaple.domain.address.AddressMapperImpl;
import com.example.studentjpaexmaple.domain.address.dto.AddressDto;
import com.example.studentjpaexmaple.domain.person.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(classes = AddressMapperImpl.class)
class AddressMapperTest {

    @Autowired
    AddressMapper addressMapper;


    @Test
    void should_map_Address_to_AddressDto_when_params_are_correct() {
        //given
        Person person = new Person(UUID.randomUUID(), "John", "Doe", LocalDate.of(2000, 2, 17), Collections.emptyList());
        Address address = new Address(UUID.randomUUID(), "someStreetname", "6423-13212", "102b", "2", true, person);

        //when
        AddressDto addressDto = addressMapper.maptoAddressDto(address);

        //then
        assertAll(
                () -> assertThat(addressDto.getId()).isEqualTo(address.getId()),
                () -> assertThat(addressDto.getStreetName()).isEqualTo(address.getStreetName()),
                () -> assertThat(addressDto.getCommuneCode()).isEqualTo(address.getCommuneCode()),
                () -> assertThat(addressDto.getHouseNumber()).isEqualTo(address.getHouseNumber()),
                () -> assertThat(addressDto.getFlatNumber()).isEqualTo(address.getFlatNumber()),
                () -> assertThat(addressDto.isDefault()).isEqualTo(address.isDefault())
        );
    }

    @Test
    void should_map_AddressDto_to_Address_when_params_ara_correct() {
        //given
        AddressDto addressDto = new AddressDto(UUID.randomUUID(), "someStreetname", "6423-13212", "102b", "2", true);

        //when
        Address address = addressMapper.mapToAddress(addressDto);

        //then
        assertAll(
                () -> assertThat(address.getId()).isEqualTo(addressDto.getId()),
                () -> assertThat(address.getStreetName()).isEqualTo(addressDto.getStreetName()),
                () -> assertThat(address.getCommuneCode()).isEqualTo(addressDto.getCommuneCode()),
                () -> assertThat(address.getHouseNumber()).isEqualTo(addressDto.getHouseNumber()),
                () -> assertThat(address.getFlatNumber()).isEqualTo(addressDto.getFlatNumber()),
                () -> assertThat(address.isDefault()).isEqualTo(addressDto.isDefault())
        );
    }
}