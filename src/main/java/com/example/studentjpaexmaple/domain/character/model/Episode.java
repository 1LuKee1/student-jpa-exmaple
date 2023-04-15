package com.example.studentjpaexmaple.domain.character.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "episode")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @ManyToMany
    @JsonIgnore
    private List<Character> character;
    @JsonValue
    private String title;

    public Episode(String title) {
        this.title = title;
    }
}
