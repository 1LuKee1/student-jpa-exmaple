package com.example.studentjpaexmaple.domain.person.dto;


import com.example.studentjpaexmaple.domain.address.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class PersonDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private AddressDto defaultAddress;
    private List<AddressDto> addresses;
}
