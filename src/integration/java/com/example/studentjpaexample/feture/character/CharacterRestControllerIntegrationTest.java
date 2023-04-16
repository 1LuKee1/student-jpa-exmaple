package com.example.studentjpaexample.feture.character;

import com.example.studentjpaexample.BaseIntegrationTest;
import com.example.studentjpaexmaple.domain.character.dto.CharacterResponse;
import com.example.studentjpaexmaple.infrastructure.character.http.controller.CharacterController;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CharacterRestControllerIntegrationTest extends BaseIntegrationTest implements SampleCharacterResponse, SampleCharacterExpectedResult {

    @Autowired
    CharacterController characterController;

    @Test
    void should_return_exactly_same_object_as_expect() throws Exception {
        final String searchedCharacterId = "1";
        wireMockServer.stubFor(WireMock.get("/api/character")
                .withQueryParam("id", equalTo(searchedCharacterId))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithOneCharacterJson())));

        CharacterResponse characterResponse = characterController.getCharacterResponseForTest(searchedCharacterId).getBody();

        assertAll(
                () -> assertThat(characterResponse).isNotNull(),
                () -> assertThat(characterResponse).isEqualTo(getCharacterExpectedResult())
        );
    }
}

