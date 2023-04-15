package com.example.studentjpaexmaple.domain.person.dto;


import com.example.studentjpaexmaple.domain.address.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    @JsonIgnore
    private AddressDto defaultAddress;
    @JsonIgnore
    private List<AddressDto> addresses;
}
