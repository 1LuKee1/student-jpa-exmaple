package com.example.studentjpaexmaple.domain.address;

import com.example.studentjpaexmaple.domain.person.PersonRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


class AddressServiceTest {

    private final AddressRepository addressRepository = mock(AddressRepository.class);
    private final PersonRepository personRepository = mock(PersonRepository.class);
    private final AddressService addressService = new AddressService(addressRepository, personRepository);


    @Test
    void should_return_all_address() {
        //given
        Address address1 = new Address(UUID.randomUUID(), "someStreetname", "6423-13212", "102b", "2", true);
        Address address2 = new Address(UUID.randomUUID(), "someStreetname", "6423-13212", "102b", "2", true);

        given(addressRepository.findAll()).willReturn(List.of(address1, address2));

        //when
        List<Address> allAddresses = addressService.getAllAddresses();

        //then
        assertAll(
                () -> assertThat(allAddresses).hasSize(2),
                () -> assertThat(allAddresses).containsExactlyInAnyOrder(
                        new Address(address1.getId(), "someStreetname", "6423-13212", "102b", "2", true),
                        new Address(address2.getId(), "someStreetname", "6423-13212", "102b", "2", true)
                )
        );
    }

    @Test
    void should_save_address_when_parameters_are_correct() {
        //given
        Address address = new Address(UUID.randomUUID(), "someStreetname", "6423-13212", "102b", "2", true);
        given(addressRepository.saveAndFlush(address)).willReturn(address);
        given(addressRepository.findAll()).willReturn(List.of(address));

        //when
        Address savedAddress = addressService.saveAddress(address);
        List<Address> allAddresses = addressService.getAllAddresses();

        //then
        assertAll(
                () -> assertThat(allAddresses).hasSize(1),
                () -> assertThat(allAddresses).containsExactlyInAnyOrder(savedAddress)
        );
    }

    @Test
    void should_delete_address_when_id_is_valid() {
        // given
        UUID addressId = UUID.randomUUID();
        Address address = new Address(addressId, "someStreetname", "6423-13212", "102b", "2", true);
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        // when
        addressService.deleteAddressById(addressId);

        // then
        verify(addressRepository, times(1)).deleteById(addressId);
    }



    @Test
    void should_return_address_find_by_id_when_addressId_is_present() {
        //given
        UUID addressId = UUID.randomUUID();
        Address address1 = new Address(UUID.randomUUID(), "someStreetname1", "6423-13212", "102b", "2", true);
        given(addressRepository.findById(addressId)).willReturn(Optional.of(address1));

        //when
        Address addressById = addressService.getAddressById(addressId);

        //then
        assertAll(
                () -> assertEquals(addressById.getId(), address1.getId()),
                () -> assertEquals(addressById.getStreetName(), address1.getStreetName()),
                () -> assertEquals(addressById.getCommuneCode(), address1.getCommuneCode()),
                () -> assertEquals(addressById.getHouseNumber(), address1.getHouseNumber()),
                () -> assertEquals(addressById.getFlatNumber(), address1.getFlatNumber()),
                () -> assertEquals(addressById.isDefault(), address1.isDefault())
        );
    }
}