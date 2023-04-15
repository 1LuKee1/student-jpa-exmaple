package com.example.studentjpaexmaple.domain.character.model;

import com.example.studentjpaexmaple.domain.character.model.Character;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String name;
    private String url;
    @JsonIgnore
    @OneToOne(
            mappedBy = "origin",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Character character;

    public Origin(String name, String url) {
        this.name = name;
        this.url = url;
    }
}

