package com.example.studentjpaexmaple.domain.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    Optional<Address> findById(UUID id);

    void deleteById(UUID id);
}
