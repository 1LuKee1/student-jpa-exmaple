package com.example.studentjpaexmaple.infrastructure.character.http.controller;

import com.example.studentjpaexmaple.domain.character.*;
import com.example.studentjpaexmaple.domain.character.model.Character;
import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import com.example.studentjpaexmaple.domain.character.model.CharacterListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    @GetMapping("/characters/{page}")
    public ResponseEntity<CharacterListResponse> fetchListOfCharacters(@PathVariable int page) {
        CharacterListResponse listResponse = characterService.fetchListOfCharacters(page);
        return ResponseEntity.ok().body(listResponse);
    }


    @GetMapping("save/{id}")
    public ResponseEntity<CharacterResponse> fetchSingleCharacter(@PathVariable String id) {
        CharacterResponse characterResponse = characterService.fetchSingleCharacter(id);
        return ResponseEntity.ok().body(characterResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<CharacterResponse> getCharacterResponseForTest(@PathVariable String id) {
        CharacterResponse characterResponse = characterService.getCharacterForTest(id);
        return ResponseEntity.ok().body(characterResponse);
    }


    @GetMapping("/saved/{id}")
    public ResponseEntity<CharacterResponse> findCharacterById(@PathVariable Integer id) {
        Character characterById = characterService.findCharacterById(id);
        CharacterResponse characterResponse = characterMapper.mapToCharacterResponse(characterById);
        return ResponseEntity.ok().body(characterResponse);
    }

    @GetMapping("/saved/all")
    public ResponseEntity<List<Character>> findAll() {
        List<Character> characterList = characterService.findAll();
        return ResponseEntity.ok().body(characterList);
    }
}
