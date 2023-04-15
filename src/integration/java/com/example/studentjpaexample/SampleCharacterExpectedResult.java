package com.example.studentjpaexample;

import com.example.studentjpaexmaple.domain.character.model.Episode;
import com.example.studentjpaexmaple.domain.character.model.Location;
import com.example.studentjpaexmaple.domain.character.model.Origin;
import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;

import java.util.List;

public interface SampleCharacterExpectedResult {

    default CharacterResponse getCharacterExpectedResult() {
        return new CharacterResponse(1, "Rick Sanchez", "Alive", "Human", "", "Male",
                new Origin("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
                new Location("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                List.of(new Episode("https://rickandmortyapi.com/api/episode/1"),
                        new Episode("https://rickandmortyapi.com/api/episode/2"),
                        new Episode("https://rickandmortyapi.com/api/episode/3"),
                        new Episode("https://rickandmortyapi.com/api/episode/4"),
                        new Episode("https://rickandmortyapi.com/api/episode/5"),
                        new Episode("https://rickandmortyapi.com/api/episode/6"),
                        new Episode("https://rickandmortyapi.com/api/episode/7"),
                        new Episode("https://rickandmortyapi.com/api/episode/8"),
                        new Episode("https://rickandmortyapi.com/api/episode/9"),
                        new Episode("https://rickandmortyapi.com/api/episode/10"),
                        new Episode("https://rickandmortyapi.com/api/episode/11"),
                        new Episode("https://rickandmortyapi.com/api/episode/12"),
                        new Episode("https://rickandmortyapi.com/api/episode/13"),
                        new Episode("https://rickandmortyapi.com/api/episode/14"),
                        new Episode("https://rickandmortyapi.com/api/episode/15"),
                        new Episode("https://rickandmortyapi.com/api/episode/16"),
                        new Episode("https://rickandmortyapi.com/api/episode/17"),
                        new Episode("https://rickandmortyapi.com/api/episode/18"),
                        new Episode("https://rickandmortyapi.com/api/episode/19"),
                        new Episode("https://rickandmortyapi.com/api/episode/20"),
                        new Episode("https://rickandmortyapi.com/api/episode/21"),
                        new Episode("https://rickandmortyapi.com/api/episode/22"),
                        new Episode("https://rickandmortyapi.com/api/episode/23"),
                        new Episode("https://rickandmortyapi.com/api/episode/24"),
                        new Episode("https://rickandmortyapi.com/api/episode/25"),
                        new Episode("https://rickandmortyapi.com/api/episode/26"),
                        new Episode("https://rickandmortyapi.com/api/episode/27"),
                        new Episode("https://rickandmortyapi.com/api/episode/28"),
                        new Episode("https://rickandmortyapi.com/api/episode/29"),
                        new Episode("https://rickandmortyapi.com/api/episode/30"),
                        new Episode("https://rickandmortyapi.com/api/episode/31"),
                        new Episode("https://rickandmortyapi.com/api/episode/32"),
                        new Episode("https://rickandmortyapi.com/api/episode/33"),
                        new Episode("https://rickandmortyapi.com/api/episode/34"),
                        new Episode("https://rickandmortyapi.com/api/episode/35"),
                        new Episode("https://rickandmortyapi.com/api/episode/36"),
                        new Episode("https://rickandmortyapi.com/api/episode/37"),
                        new Episode("https://rickandmortyapi.com/api/episode/38"),
                        new Episode("https://rickandmortyapi.com/api/episode/39"),
                        new Episode("https://rickandmortyapi.com/api/episode/40"),
                        new Episode("https://rickandmortyapi.com/api/episode/41"),
                        new Episode("https://rickandmortyapi.com/api/episode/42"),
                        new Episode("https://rickandmortyapi.com/api/episode/43"),
                        new Episode("https://rickandmortyapi.com/api/episode/44"),
                        new Episode("https://rickandmortyapi.com/api/episode/45"),
                        new Episode("https://rickandmortyapi.com/api/episode/46"),
                        new Episode("https://rickandmortyapi.com/api/episode/47"),
                        new Episode("https://rickandmortyapi.com/api/episode/48"),
                        new Episode("https://rickandmortyapi.com/api/episode/49"),
                        new Episode("https://rickandmortyapi.com/api/episode/50"),
                        new Episode("https://rickandmortyapi.com/api/episode/51")),
                "https://rickandmortyapi.com/api/character/1",
                "2017-11-04T18:48:46.250Z");

    }
}
