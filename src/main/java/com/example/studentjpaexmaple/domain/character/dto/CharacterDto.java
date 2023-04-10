package com.example.studentjpaexmaple.domain.character.dto;

import com.example.studentjpaexmaple.domain.character.Episode;
import com.example.studentjpaexmaple.domain.character.Location;
import com.example.studentjpaexmaple.domain.character.Origin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CharacterDto {
    private final Integer id;
    private final String name;
    private final String status;
    private final String species;
    private final String type;
    private final String gender;
    private final Origin origin;
    private final Location location;
    private final String image;
    private final List<Episode> episode;
    private final String url;
    private final String created;
}


