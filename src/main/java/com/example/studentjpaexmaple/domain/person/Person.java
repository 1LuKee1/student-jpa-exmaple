package com.example.studentjpaexmaple.domain.person;

import com.example.studentjpaexmaple.domain.address.Address;
import lombok.*;
import org.hibernate.annotations.Type;

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
public class Person {
    @Id
    @GeneratedValue
    private UUID id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private LocalDate birthDate;
    @NonNull
    @OneToMany(mappedBy = "person")
    private List<Address> addresses;
}
