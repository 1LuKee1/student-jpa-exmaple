package com.example.studentjpaexmaple.domain.character.dto;

import com.example.studentjpaexmaple.domain.character.model.Episode;
import com.example.studentjpaexmaple.domain.character.model.Location;
import com.example.studentjpaexmaple.domain.character.model.Origin;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "status", "species", "type", "gender", "origin", "location", "image", "episode", "url", "created"})
public class CharacterResponse {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private Location location;
    private String image;
    @JsonProperty("episode")
    private List<Episode> episodes;
    private String url;
    private String created;
}
