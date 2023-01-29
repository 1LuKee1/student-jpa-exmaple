package com.example.studentjpaexmaple.service;

import com.example.studentjpaexmaple.entity.Address;
import com.example.studentjpaexmaple.entity.Student;
import com.example.studentjpaexmaple.repository.AddressRepository;
import com.example.studentjpaexmaple.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final AddressRepository addressRepository;

    public StudentService(StudentRepository studentRepository, AddressRepository addressRepository) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with id " + id + " doesn't exist."));
    }

    public Student findStudentByName(String name) {
        List<Student> studentList = studentRepository.findAll();
        for (Student student : studentList) {
            if (!student.getName().equals(name)) {
                throw new RuntimeException("Student with name: " + name + " doesn't exist");
            }

        }
        return studentRepository.findStudentByName(name);
    }


    @Transactional
    public void deleteStudentById(Long id) {
        if (id == 0) {
            throw new RuntimeException("You need to provide ID of student to be deleted. ID can not be 0.");
        }
        Optional<Student> checkIfStudentWithIdExist = studentRepository.findById(id);
        if (checkIfStudentWithIdExist.isEmpty()) {
            throw new RuntimeException(
                    "Student can not be deleted because student with id: " + id + " doesn't exist."
            );
        }
        studentRepository.deleteStudentById(id);
    }

    @Transactional
    public Student updateStudentName(Student updatedStudent) {
        Student student = studentRepository.findById(updatedStudent.getId())
                .orElseThrow(() -> new RuntimeException("Student doesn't exist"));
        student.setName(updatedStudent.getName());
        return studentRepository.save(student);
    }

    public Address registerAddressForStudent(Long id, Address address) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty()) {
            throw new RuntimeException("Student with id: " + id + " doesn't exist.");
        }
        address.setStudent(student.get());
        return addressRepository.save(address);
    }

    public List<Address> getAddressesListByIdForStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new RuntimeException("Student with id: " + id + " doesn't exist.");
        }
        return student.get().getAddress();
    }

    public Address getAddressByIdForStudentById(Long studentId, Long addressId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new RuntimeException("Student with id: " + studentId + " doesn't exist.");
        }
        List<Address> addresses = student.get().getAddress();
        for (Address address : addresses) {
            if (address.getId().equals(addressId)) {
                return address;
            }
        }
        throw new RuntimeException("Address with id: " + addressId + " doesn't exist.");
    }


}
