package com.example.studentjpaexmaple.domain.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findById(UUID id);

    Optional<Person> findPersonByFirstName(String firstName);

    boolean existsByFirstName(String firstName);

    boolean existsById(UUID id);

    void deleteById(UUID uuid);

}
