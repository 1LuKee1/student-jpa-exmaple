package com.example.studentjpaexmaple.domain.person;

import com.example.studentjpaexmaple.domain.person.dto.PersonDto;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class PersonValidator {

    private final TimeProvider timeProvider;

    public boolean isPersonAdult(Person person) {
        LocalDate yearValidation = timeProvider.getActualDate().minusYears(18);
        return person.getBirthDate().isBefore(yearValidation);
    }
}
