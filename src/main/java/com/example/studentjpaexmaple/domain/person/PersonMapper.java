package com.example.studentjpaexmaple.domain.person;

import com.example.studentjpaexmaple.domain.address.AddressMapper;
import com.example.studentjpaexmaple.domain.person.dto.PersonDto;
import org.mapstruct.*;

@Mapper(uses = AddressMapper.class,
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.FIELD,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PersonMapper {

    Person mapToPerson(PersonDto personDTO);

    @Mapping(source = "person", target = "defaultAddress", qualifiedByName = "defaultAddress")
    @Mapping(source = "person", target = "addresses", qualifiedByName = "addresses")
    PersonDto mapToPersonDto(Person person);
}


