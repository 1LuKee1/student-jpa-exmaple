package com.example.studentjpaexmaple.domain.address;

import com.example.studentjpaexmaple.domain.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String streetName;
    private String communeCode;
    private String houseNumber;
    private String flatNumber;
    private boolean isDefault;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
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