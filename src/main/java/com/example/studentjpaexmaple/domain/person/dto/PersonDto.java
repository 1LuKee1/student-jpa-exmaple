package com.example.studentjpaexmaple.domain.person.dto;


import com.example.studentjpaexmaple.domain.address.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private UUID id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private AddressDto defaultAddress;
    private List<AddressDto> addresses;


    public PersonDto(UUID id, String firstName, String lastName, LocalDate birthDate, List<AddressDto> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.addresses = addresses;
    }
}
