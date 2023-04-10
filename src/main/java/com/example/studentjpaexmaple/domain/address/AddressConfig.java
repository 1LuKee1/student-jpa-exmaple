package com.example.studentjpaexmaple.domain.address;

import com.example.studentjpaexmaple.domain.person.PersonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AddressConfig {

    @Bean
    AddressService addressService(AddressRepository addressRepository, PersonRepository personRepository) {
        return new AddressService(addressRepository, personRepository);
    }
}
