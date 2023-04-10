package com.example.studentjpaexmaple.domain.character;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CharacterConfig {

    @Bean
    CharacterService characterService(CharacterRepository characterRepository, CharacterMapper characterMapper , CharacterFetcherClient characterFetcherClient) {
        return new CharacterService(characterRepository, characterMapper, characterFetcherClient);
    }
}
