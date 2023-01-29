package com.example.studentjpaexmaple.repository;

import com.example.studentjpaexmaple.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findById(Long id);

    @Transactional
    void deleteStudentById(Long id);


    Student findStudentByName(String name);


}
