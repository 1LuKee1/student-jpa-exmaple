package com.example.studentjpaexmaple.domain.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private UUID id;
    private String streetName;
    private String communeCode;
    private String houseNumber;
    private String flatNumber;
    private boolean isDefault;
}
