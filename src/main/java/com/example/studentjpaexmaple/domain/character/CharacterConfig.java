package com.example.studentjpaexmaple.domain.character;

import com.example.studentjpaexmaple.infrastructure.character.http.CharacterFetcherServiceHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CharacterConfig {

    @Bean
    CharacterService characterService(CharacterRepository characterRepository,
                                      CharacterMapper characterMapper,
                                      CharactersFetcher characterFetcher,
                                      CharacterFetcherServiceHttpClient characterFetcherServiceHttpClient) {
        return new CharacterService(characterRepository, characterMapper, characterFetcher, characterFetcherServiceHttpClient);
    }
}
