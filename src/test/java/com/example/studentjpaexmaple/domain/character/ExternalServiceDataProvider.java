package com.example.studentjpaexmaple.domain.character;

import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import com.example.studentjpaexmaple.domain.character.model.Episode;
import com.example.studentjpaexmaple.domain.character.model.Location;
import com.example.studentjpaexmaple.domain.character.model.Origin;

import java.util.List;

interface ExternalServiceDataProvider {

    default CharacterResponse rickSanchezCharacterResponse() {
        return new CharacterResponse(1, "Rick Sanchez", "Alive", "Human", "", "Male",
                new Origin("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
                new Location("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                List.of(new Episode("https://rickandmortyapi.com/api/episode/1"),
                        new Episode("https://rickandmortyapi.com/api/episode/2"),
                        new Episode("https://rickandmortyapi.com/api/episode/3")),
                "https://rickandmortyapi.com/api/character/1",
                "2017-11-04T18:48:46.250Z");
    }

    default CharacterResponse mortySmithCharacterResponse() {
        return new CharacterResponse(2, "Morty Smith", "Alive", "Human", "", "Male",
                new Origin("unknown", "https://rickandmortyapi.com/api/location/3"),
                new Location("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                List.of(new Episode("https://rickandmortyapi.com/api/episode/1"),
                        new Episode("https://rickandmortyapi.com/api/episode/2"),
                        new Episode("https://rickandmortyapi.com/api/episode/3")),
                "https://rickandmortyapi.com/api/character/2",
                "2017-11-04T18:50:21.651Z)");
    }
}
