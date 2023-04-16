package com.example.studentjpaexmaple.infrastructure.character.http;

import com.example.studentjpaexmaple.domain.character.CharactersFetcher;
import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import com.example.studentjpaexmaple.domain.character.model.CharacterListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "characterFetcherClient",
        url = "${http.client.url}",
        path = "/api/character"
)
public interface CharacterFetcherServiceHttpClient extends CharactersFetcher {

    @GetMapping("/{id}")
    CharacterResponse fetchSingleCharacter(@PathVariable("id") String id);

    @GetMapping()
    CharacterListResponse fetchListOfCharacters(@RequestParam(value = "page", defaultValue = "1") int page);


}
