package com.example.studentjpaexmaple.infrastructure.address.controller;


import com.example.studentjpaexmaple.domain.address.Address;
import com.example.studentjpaexmaple.domain.address.AddressMapper;
import com.example.studentjpaexmaple.domain.address.AddressService;
import com.example.studentjpaexmaple.domain.address.dto.AddressDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @PostMapping
    public ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto inputAddressDto) {
        Address address = addressMapper.mapToAddress(inputAddressDto);
        Address savedAddress = addressService.saveAddress(address);
        AddressDto addressDto = addressMapper.maptoAddressDto(savedAddress);
        return new ResponseEntity<>(addressDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAddresses() {
        List<Address> allAddresses = addressService.getAllAddresses();
        List<AddressDto> addressDtos = allAddresses.stream()
                .map(addressMapper::maptoAddressDto)
                .toList();
        return new ResponseEntity<>(addressDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable UUID addressId) {
        addressService.deleteAddressById(addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    ResponseEntity<AddressDto> getAddressById(@PathVariable UUID addressId) {
        Address addressById = addressService.getAddressById(addressId);
        AddressDto addressDto = addressMapper.maptoAddressDto(addressById);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }
}
