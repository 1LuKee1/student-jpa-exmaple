package com.example.studentjpaexmaple.domain.character;

import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import com.example.studentjpaexmaple.domain.character.model.Character;
import com.example.studentjpaexmaple.domain.character.model.CharacterListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CharacterMapper {

    List<Character> mapToCharacter(List<CharacterResponse> characterResponse);

    CharacterResponse mapToCharacterResponse(Character character);

    @Mapping(target = "characterListResponse", ignore = true)
    Character mapToCharacter(CharacterResponse character);


    @Mapping(source = "characterListResponse.id", target = "id")
    @Mapping(source = "savedCharactersList", target = "charactersResponse")
    @Mapping(source = "characterListResponse.info", target = "info")
    CharacterListResponse mapToCharacterListResponse(List<Character> savedCharactersList, CharacterListResponse characterListResponse);
}
