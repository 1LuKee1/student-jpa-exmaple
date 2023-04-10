package com.example.studentjpaexmaple.infrastructure.character.controller;

import com.example.studentjpaexmaple.domain.character.Character;
import com.example.studentjpaexmaple.domain.character.CharacterMapper;
import com.example.studentjpaexmaple.domain.character.CharacterService;
import com.example.studentjpaexmaple.domain.character.dto.CharacterDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("/character")
@AllArgsConstructor
class CharacterController {

    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    @GetMapping
    public ResponseEntity<List<CharacterDto>> fetchCharacterAndSave() {
        List<Character> characters = characterService.fetchCharacterAndSave();
        List<CharacterDto> characterDtos = characterMapper.mapToCharacterDto(characters);
        return ResponseEntity.ok().body(characterDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> findCharacterById(@PathVariable Integer id) {
        Character characterById = characterService.findCharacterById(id);
        CharacterDto characterDto = characterMapper.mapToCharacterDto(characterById);
        return ResponseEntity.ok().body(characterDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharacterDto>> findAll() {
        List<Character> characterList = characterService.findAll();
        List<CharacterDto> characterDtos = characterList.stream()
                .map(characterMapper::mapToCharacterDto)
                .toList();
        return ResponseEntity.ok().body(characterDtos);
    }
}
