package com.example.studentjpaexmaple.domain.person;

import com.example.studentjpaexmaple.domain.address.Address;
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
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    @OneToMany(
            mappedBy = "person",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Address> addresses;

    public Person(String firstName, String lastName, LocalDate birthDate, List<Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.addresses = addresses;
    }
}
