package com.example.studentjpaexmaple.domain.character;

import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import com.example.studentjpaexmaple.infrastructure.client.CharacterHttpClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "CharacterFetcherClient",
        url = "${http.client.url}",
        configuration = CharacterHttpClientConfig.class
)
public interface CharacterFetcherClient {

    @RequestMapping(method = RequestMethod.GET, value = "/character")
    List<CharacterResponse> getCharacter();
}
