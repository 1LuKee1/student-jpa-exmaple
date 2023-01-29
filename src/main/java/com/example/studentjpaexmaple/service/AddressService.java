package com.example.studentjpaexmaple.service;


import com.example.studentjpaexmaple.entity.Address;
import com.example.studentjpaexmaple.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {


    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }


    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }


    public void deleteAddressById(Long id) {
        if (id == 0) {
            throw new RuntimeException("You need to provide ID of address to be deleted. ID can not be 0.");
        }
        Optional<Address> checkIfAddressWithIdExist = addressRepository.findById(id);
        if (checkIfAddressWithIdExist.isEmpty()) {
            throw new RuntimeException(
                    "Address can not be deleted because address with id: " + id + " does not exist.");
        }
        addressRepository.deleteById(id);
    }


}
