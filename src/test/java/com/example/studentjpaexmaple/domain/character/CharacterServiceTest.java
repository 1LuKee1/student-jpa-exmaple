package com.example.studentjpaexmaple.domain.character;

import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatRuntimeException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CharacterServiceTest implements ExternalServiceDataProvider {

    private final CharacterRepository characterRepository = mock(CharacterRepository.class);
    private final CharacterFetcherClient client = mock(CharacterFetcherClient.class);
    private final CharacterMapper characterMapper = Mappers.getMapper(CharacterMapper.class);
    private final CharacterService characterService = new CharacterService(characterRepository, characterMapper, client);


    @Test
    void should_fetch_character_and_save_when_there_are_character_in_external_server() {
        //given
        List<CharacterResponse> characterResponse = offersToFetch();
        List<Character> characters = characterMapper.mapToCharacter(characterResponse);
        Character rick = characterMapper.mapToCharacter(rickSanchezCharacterResponse());
        Character morty = characterMapper.mapToCharacter(mortySmithCharacterResponse());

        given(client.getCharacter()).willReturn(characterResponse);
        given(characterRepository.saveAllAndFlush(characters)).willReturn(characters);

        //when
        List<Character> fetchedCharacters = characterService.fetchCharacterAndSave();

        //then
        assertAll(
                () -> assertThat(fetchedCharacters).hasSize(2),
                () -> assertThat(fetchedCharacters).containsExactlyInAnyOrder(rick, morty)
        );
    }

    @Test
    void should_find_character_by_id_when_character_exist_by_id() {
        //given
        Character rick = characterMapper.mapToCharacter(rickSanchezCharacterResponse());
        given(characterRepository.findById(rick.getId())).willReturn(Optional.of(rick));

        //when
        Character characterById = characterService.findCharacterById(rick.getId());

        //then
        assertAll(
                () -> assertThat(characterById).isEqualTo(rick)
        );
    }

    @Test
    void should_find_all_character_when_there_are_in_database() {
        //given
        List<CharacterResponse> characterResponses = offersToFetch();
        List<Character> charactersInDatabase = characterMapper.mapToCharacter(characterResponses);
        CharacterResponse rickSanchezCharacterResponse = rickSanchezCharacterResponse();
        CharacterResponse mortySmithCharacterResponse = mortySmithCharacterResponse();
        Character mappedRick = characterMapper.mapToCharacter(rickSanchezCharacterResponse);
        Character mappedMorty = characterMapper.mapToCharacter(mortySmithCharacterResponse);
        given(characterRepository.findAll()).willReturn(charactersInDatabase);

        //when
        List<Character> characters = characterService.findAll();

        //then
        assertAll(
                () -> assertThat(characters).hasSize(2),
                () -> assertThat(characters).containsExactlyInAnyOrder(mappedRick, mappedMorty)
        );
    }

    @Test
    void should_throw_exception_when_character_does_not_exist_by_id() {
        //given
        Integer searchedCharacterById = 3;
        given(characterRepository.findById(searchedCharacterById))
                .willThrow(new RuntimeException(String.format("Could not find character by id: %d", searchedCharacterById)));
        //when
        //then
        assertThatRuntimeException()
                .isThrownBy(() -> characterService
                        .findCharacterById(searchedCharacterById))
                .withMessage(String.format("Could not find character by id: %d", searchedCharacterById));
    }
}