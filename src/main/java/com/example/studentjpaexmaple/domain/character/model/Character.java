package com.example.studentjpaexmaple.domain.character.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id", referencedColumnName = "id")
    private Origin origin;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
    private String image;
    @ManyToMany(
            mappedBy = "character",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    private List<Episode> episodes;
    private String url;
    private String created;
    @ManyToOne
    @JsonIgnore
    private CharacterListResponse characterListResponse;
}


