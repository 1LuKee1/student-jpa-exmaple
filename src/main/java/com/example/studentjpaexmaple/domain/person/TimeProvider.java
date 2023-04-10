package com.example.studentjpaexmaple.domain.person;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TimeProvider {

    public LocalDate getActualDate() {
        return LocalDate.of(2023, 3, 31);
    }
}
