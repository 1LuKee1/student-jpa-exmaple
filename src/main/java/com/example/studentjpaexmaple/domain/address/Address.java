package com.example.studentjpaexmaple.domain.address;

import com.example.studentjpaexmaple.domain.person.Person;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    @NonNull
    private UUID id;
    @NonNull
    private String streetName;
    @NonNull
    private String communeCode;
    @NonNull
    private String houseNumber;
    @NonNull
    private String flatNumber;
    @NonNull
    private boolean isDefault;
    @ManyToOne
    @JoinColumn(name = "person_lastname", referencedColumnName = "lastname")
    private Person person;
}