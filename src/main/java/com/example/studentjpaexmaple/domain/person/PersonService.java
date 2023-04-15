package com.example.studentjpaexmaple.domain.person;

import com.example.studentjpaexmaple.domain.address.Address;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonValidator validator;

    public Person createPerson(Person personToSave) {
        if (personRepository.findById(personToSave.getId()).isPresent()) {
            throw new RuntimeException("Person " + personToSave.getId() + " already exists");
        }
        return personRepository.saveAndFlush(personToSave);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person findPersonById(UUID personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person with id " + personId + " doesn't exist."));
    }

    public List<Address> getAddressesByPersonId(UUID personId) {
        return personRepository.findById(personId)
                .map(Person::getAddresses)
                .orElse(Collections.emptyList())
                .stream()
                .toList();
    }

    public Person findPersonByFirstName(String name) {
        return personRepository.findPersonByFirstName(name)
                .orElseThrow(() -> new RuntimeException("Person with name: " + name + " doesn't exist"));
    }

    @Transactional
    public void deletePersonById(UUID personId) {
        if (isNull(personId)) {
            throw new RuntimeException("You need to provide ID of person to be deleted. ID can not be 0.");
        }
        Optional<Person> personRepositoryById = personRepository.findById(personId);
        if (personRepositoryById.isEmpty()) {
            throw new RuntimeException(
                    "Person can not be deleted because person with id: " + personId + " doesn't exist."
            );
        }
        personRepository.deleteById(personId);
    }

    public List<Person> getAllAdultsPersons() {
        return personRepository.findAll()
                .stream()
                .filter(validator::isPersonAdult)
                .toList();
    }

    @Transactional
    public Person updatePerson(UUID personID, Person person) {
        Person personToUpdate = personRepository.findById(personID)
                .orElseThrow(() -> new RuntimeException("Person with id " + person.getId() + " doesn't exist."));
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        personToUpdate.setBirthDate(person.getBirthDate());
        return personRepository.saveAndFlush(personToUpdate);
    }
}
