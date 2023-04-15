package com.example.studentjpaexmaple.domain.character;

import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import com.example.studentjpaexmaple.domain.character.model.Character;
import com.example.studentjpaexmaple.infrastructure.character.http.CharacterFetcherServiceHttpClient;
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
    private final CharacterFetcherServiceHttpClient client = mock(CharacterFetcherServiceHttpClient.class);
    private final CharacterMapper characterMapper = Mappers.getMapper(CharacterMapper.class);
    private final CharactersFetcher charactersFetcher = mock(CharactersFetcher.class);
    private final CharacterService characterService = new CharacterService(characterRepository, characterMapper, charactersFetcher, client);


    @Test
    void should_fetch_character_and_save_when_there_are_character_in_external_server() {
        //given
        final String searchedCharacterId = "1";

        CharacterResponse rickSanchezCharacterResponse = rickSanchezCharacterResponse();
        Character character = characterMapper.mapToCharacter(rickSanchezCharacterResponse);

        given(characterRepository.saveAndFlush(character)).willReturn(character);
        given(charactersFetcher.fetchSingleCharacter(searchedCharacterId)).willReturn(rickSanchezCharacterResponse);

        //when
        CharacterResponse characterResponse = characterService.fetchSingleCharacter(searchedCharacterId);

        //then
        assertAll(
                () -> assertThat(characterResponse).isNotNull(),
                () -> assertThat(characterResponse).isEqualTo(rickSanchezCharacterResponse)
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
        CharacterResponse rickSanchezCharacterResponse = rickSanchezCharacterResponse();
        CharacterResponse mortySmithCharacterResponse = mortySmithCharacterResponse();
        Character mappedRick = characterMapper.mapToCharacter(rickSanchezCharacterResponse);
        Character mappedMorty = characterMapper.mapToCharacter(mortySmithCharacterResponse);
        List<Character> charactersInDatabase = List.of(mappedRick, mappedMorty);
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