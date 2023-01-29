package com.example.studentjpaexmaple.controller;

import com.example.studentjpaexmaple.entity.Address;
import com.example.studentjpaexmaple.entity.Student;
import com.example.studentjpaexmaple.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long studentId) {
        Student student = studentService.findStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/name/{studentName}")
    public ResponseEntity<Student> findStudentByName(@PathVariable String studentName) {
        Student student = studentService.findStudentByName(studentName);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudentName(@RequestBody Student upadtedStudent) {
        Student student = studentService.updateStudentName(upadtedStudent);
        return new ResponseEntity<>(student, HttpStatus.ACCEPTED);
    }

    @PostMapping("/{studentId}/addresses")
    public ResponseEntity<List<Address>> createAddressForStudent(@PathVariable Long studentId,
                                                                 @RequestBody Address address) {

        Address savedAddress = studentService.registerAddressForStudent(studentId, address);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAddress.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{studentId}/addresses")
    public ResponseEntity<List<Address>> getAddressesByIdForStudentById(@PathVariable Long studentId) {
        List<Address> addresses = studentService.getAddressesListByIdForStudentById(studentId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/{studentId}/addresses/{addressId}")
    public ResponseEntity<Address> getAddressByIdForStudentById(@PathVariable Long studentId,
                                                                @PathVariable Long addressId) {

        Address address = studentService.getAddressByIdForStudentById(studentId, addressId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }


    @DeleteMapping("{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long studentId) {
        Student student = studentService.findStudentById(studentId);
        studentService.deleteStudentById(student.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
