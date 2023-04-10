package com.example.studentjpaexmaple.domain.character;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String status;
    @NonNull
    private String species;
    @NonNull
    private String type;
    @NonNull
    private String gender;
    @NonNull
    @OneToOne
    private Origin origin;
    @NonNull
    @OneToOne
    private Location location;
    @NonNull
    private String image;
    @NonNull
    @OneToMany
    private List<Episode> episode;
    @NonNull
    private String url;
    @NonNull
    private String created;
}


