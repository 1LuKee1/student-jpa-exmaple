package com.example.studentjpaexmaple.domain.person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PersonConfig {

    @Bean
    PersonService personService(PersonRepository personRepository) {
        TimeProvider timeProvider = new TimeProvider();
        PersonValidator validator = new PersonValidator(timeProvider);
        return new PersonService(personRepository, validator);
    }
}
