package com.example.studentjpaexmaple.domain.address;

import com.example.studentjpaexmaple.domain.address.dto.AddressDto;
import com.example.studentjpaexmaple.domain.person.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {


    @Mapping(target = "person", ignore = true)
    @Mapping(source = "default", target = "default")
    Address mapToAddress(AddressDto addressDTO);

    @Mapping(source = "default", target = "isDefault")
    AddressDto maptoAddressDto(Address address);

    @Named("defaultAddress")
    default AddressDto getDefaultAddress(Person person) {
        if (isEmpty(person.getAddresses())) {
            return null;
        }
        return person.getAddresses().stream()
                .filter(Address::isDefault)
                .map(this::maptoAddressDto)
                .findFirst()
                .orElse(null);
    }

    @Named("addresses")
    default List<AddressDto> getAddresses(Person person) {
        if (isEmpty(person.getAddresses())) {
            return Collections.emptyList();
        }
        return person.getAddresses().stream()
                .filter(addressEntity -> !addressEntity.isDefault())
                .map(this::maptoAddressDto)
                .toList();
    }
}
