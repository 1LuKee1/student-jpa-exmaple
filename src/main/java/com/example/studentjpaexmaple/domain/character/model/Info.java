package com.example.studentjpaexmaple.domain.character.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}
