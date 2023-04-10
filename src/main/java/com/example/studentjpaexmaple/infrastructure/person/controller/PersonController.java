package com.example.studentjpaexmaple.infrastructure.person.controller;

import com.example.studentjpaexmaple.domain.person.Person;
import com.example.studentjpaexmaple.domain.person.PersonMapper;
import com.example.studentjpaexmaple.domain.person.PersonService;
import com.example.studentjpaexmaple.domain.person.dto.PersonDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @PostMapping
    public ResponseEntity<PersonDto> addStudent(@RequestBody PersonDto personDto) {
        Person personToSave = personMapper.mapToPerson(personDto);
        Person person = personService.createPerson(personToSave);
        PersonDto mappedPerson = personMapper.mapToPersonDto(person);
        return new ResponseEntity<>(mappedPerson, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllPerson() {
        List<Person> allPersons = personService.getAllPersons();
        List<PersonDto> personDtos = allPersons.stream()
                .map(personMapper::mapToPersonDto)
                .toList();
        return new ResponseEntity<>(personDtos, HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> findPersonById(@PathVariable UUID personId) {
        Person personById = personService.findPersonById(personId);
        PersonDto mappedPersonById = personMapper.mapToPersonDto(personById);
        return new ResponseEntity<>(mappedPersonById, HttpStatus.OK);
    }

    @GetMapping("/personname/{personName}")
    public ResponseEntity<PersonDto> findAllAdultsPerson(@PathVariable String personName) {
        Person personByFirstName = personService.findPersonByFirstName(personName);
        PersonDto mapToPersonDto = personMapper.mapToPersonDto(personByFirstName);
        return new ResponseEntity<>(mapToPersonDto, HttpStatus.OK);
    }

    @GetMapping("/isadult")
    public ResponseEntity<List<PersonDto>> findAllAdultsPerson() {
        List<Person> allAdultsPersons = personService.getAllAdultsPersons();
        List<PersonDto> personDtos = allAdultsPersons.stream()
                .map(personMapper::mapToPersonDto)
                .toList();
        return new ResponseEntity<>(personDtos, HttpStatus.OK);
    }

    @PatchMapping("/{personId}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable UUID personId, @RequestBody PersonDto personDto) {
        Person mappedPerson = personMapper.mapToPerson(personDto);
        Person person = personService.updatePerson(personId, mappedPerson);
        PersonDto mappedPersonToDto = personMapper.mapToPersonDto(person);
        return new ResponseEntity<>(mappedPersonToDto, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("{personId}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable UUID personId) {
        Person personById = personService.findPersonById(personId);
        personService.deletePersonById(personById.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
