package com.example.studentjpaexmaple.domain.character;

import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterFetcherClient client;

    public List<Character> fetchCharacterAndSave() {
        List<CharacterResponse> characterResponse = client.getCharacter();
        List<Character> characters = characterMapper.mapToCharacter(characterResponse);
        return characterRepository.saveAllAndFlush(characters);
    }

    public Character findCharacterById(Integer id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find character by id: %d", id)));
    }

    public List<Character> findAll() {
        return characterRepository.findAll()
                .stream()
                .toList();
    }
}
