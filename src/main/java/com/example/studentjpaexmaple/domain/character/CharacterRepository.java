package com.example.studentjpaexmaple.domain.character;

import com.example.studentjpaexmaple.domain.character.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CharacterRepository extends JpaRepository<Character, Integer> {
    Optional<Character> findById(Integer id);

    boolean existsById(Integer id);

}
