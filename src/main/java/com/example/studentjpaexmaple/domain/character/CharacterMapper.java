package com.example.studentjpaexmaple.domain.character;

import com.example.studentjpaexmaple.domain.character.dto.CharacterDto;
import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    List<Character> mapToCharacter(List<CharacterResponse> characterResponse);

    List<CharacterDto> mapToCharacterDto(List<Character> character);

    CharacterDto mapToCharacterDto(Character character);

    CharacterResponse mapToCharacterResponse(Character character);

    Character mapToCharacter(CharacterResponse character);
}
