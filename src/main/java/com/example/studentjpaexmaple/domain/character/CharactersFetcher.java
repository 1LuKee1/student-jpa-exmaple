package com.example.studentjpaexmaple.domain.character;

import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import com.example.studentjpaexmaple.domain.character.model.CharacterListResponse;

public interface CharactersFetcher {
    CharacterListResponse fetchListOfCharacters(int page);
    CharacterResponse fetchSingleCharacter(String id);
}
