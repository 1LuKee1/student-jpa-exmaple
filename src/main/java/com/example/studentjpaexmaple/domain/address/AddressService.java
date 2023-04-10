package com.example.studentjpaexmaple.domain.address;


import com.example.studentjpaexmaple.domain.person.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;


@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .toList();
    }

    @Transactional
    public Address saveAddress(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    @Transactional
    public void deleteAddressById(UUID id) {
        if (isNull(id)) {
            throw new RuntimeException("You need to provide ID of address to be deleted. ID can not be 0.");
        }
        Optional<Address> checkIfAddressWithIdExist = addressRepository.findById(id);
        if (checkIfAddressWithIdExist.isEmpty()) {
            throw new RuntimeException(
                    "Address can not be deleted because address with id: " + id + " does not exist.");
        }
        addressRepository.deleteById(id);
    }


    public Address getAddressById(UUID addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address with id: " + addressId + " doesn't exist."));
    }
}
