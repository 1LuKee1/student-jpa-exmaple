package com.example.studentjpaexmaple.domain.character;

import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import com.example.studentjpaexmaple.domain.character.exception.CharacterDuplicateException;
import com.example.studentjpaexmaple.domain.character.exception.CharacterSavingException;
import com.example.studentjpaexmaple.domain.character.model.Character;
import com.example.studentjpaexmaple.domain.character.model.CharacterListResponse;
import com.example.studentjpaexmaple.infrastructure.character.http.CharacterFetcherServiceHttpClient;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CharacterService implements CharacterFetcherServiceHttpClient {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharactersFetcher charactersFetcher;
    private final CharacterFetcherServiceHttpClient client;


    @Override
    public CharacterResponse fetchSingleCharacter(String id) {
        CharacterResponse characterResponse = charactersFetcher.fetchSingleCharacter(id);
        Character character = characterMapper.mapToCharacter(characterResponse);
        Character savedCharacter = characterRepository.saveAndFlush(character);
        return characterMapper.mapToCharacterResponse(savedCharacter);
    }

    @Override
    public CharacterListResponse fetchListOfCharacters(int page) {
        CharacterListResponse characterListResponse = client.fetchListOfCharacters(page);
        List<Character> characters = filterNotExistingCharacters(characterListResponse);
        try {
            List<Character> savedCharactersList = characterRepository.saveAllAndFlush(characters);
            return characterMapper.mapToCharacterListResponse(savedCharactersList, characterListResponse);
        } catch (CharacterDuplicateException duplicateKeyException) {
            throw new CharacterSavingException(duplicateKeyException.getMessage(), characters);
        }
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

    public CharacterResponse getCharacterForTest(String id) {
        return client.fetchSingleCharacter(id);
    }

    private List<Character> filterNotExistingCharacters(CharacterListResponse characterListResponse) {
        return characterListResponse.getCharactersResponse().stream()
                .filter(character -> !characterRepository.existsById(character.getId()))
                .toList();
    }
}
