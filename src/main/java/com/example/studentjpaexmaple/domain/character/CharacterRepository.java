package com.example.studentjpaexmaple.domain.character;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CharacterRepository extends JpaRepository<Character, Long> {
    Optional<Character> findById(Integer id);
}
