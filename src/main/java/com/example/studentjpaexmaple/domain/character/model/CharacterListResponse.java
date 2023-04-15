package com.example.studentjpaexmaple.domain.character.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public
class CharacterListResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(
            mappedBy = "characterListResponse",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonProperty(value = "result")
    private List<Character> charactersResponse;
    @OneToOne
    private Info info;
}
