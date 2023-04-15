package com.example.studentjpaexmaple.domain.address;

import com.example.studentjpaexmaple.domain.person.Person;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String streetName;
    private String communeCode;
    private String houseNumber;
    private String flatNumber;
    private boolean isDefault;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;


    public Address(UUID id, String streetName, String communeCode, String houseNumber, String flatNumber, boolean isDefault) {
        this.id = id;
        this.streetName = streetName;
        this.communeCode = communeCode;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.isDefault = isDefault;
    }
}