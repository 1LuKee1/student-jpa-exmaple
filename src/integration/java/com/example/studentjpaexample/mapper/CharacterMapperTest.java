package com.example.studentjpaexample.mapper;


import com.example.studentjpaexmaple.domain.character.CharacterMapper;
import com.example.studentjpaexmaple.domain.character.CharacterMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CharacterMapperImpl.class)
class CharacterMapperTest {

    @Autowired
    CharacterMapper characterMapper;

    @Test
    void should_map_list_of_character_response_to_list_of_character() {
        //given

        //when

        //then
    }

    @Test
    void should_map_character_to_character_response() {
        //given

        //when

        //then

    }


    @Test
    void should_map_character_response_to_character() {
        //given

        //when

        //then
    }


    @Test
    void should_map_list_of_saved_character_to_character_list_response() {
        //given

        //when

        //then
    }
}
