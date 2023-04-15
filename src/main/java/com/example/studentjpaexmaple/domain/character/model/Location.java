package com.example.studentjpaexmaple.domain.character.model;

import com.example.studentjpaexmaple.domain.character.model.Character;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String name;
    private String url;
    @JsonIgnore
    @OneToOne(
            mappedBy = "location",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Character character;

    public Location(String name, String url) {
        this.name = name;
        this.url = url;
    }
}

