package com.example.demo.service;



import com.example.demo.entity.Student;

import com.example.demo.exception.DuplicateAdminNameException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    Page<Student> findAll(String name, Pageable pageable);
    Student findById(int id);
    boolean save(Student student) throws DuplicateAdminNameException;
    boolean deleteById(int id);

}
