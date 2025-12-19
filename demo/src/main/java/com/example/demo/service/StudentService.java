package com.example.demo.service;


import com.example.demo.entity.ClassCG;
import com.example.demo.entity.Student;
import com.example.demo.exception.DuplicateAdminNameException;
import com.example.demo.repository.IClassRepository;
import com.example.demo.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private IClassRepository classRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> findAll(String name, Pageable pageable) {
        return studentRepository.findByNameContaining(name,pageable);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public boolean save(Student student) throws DuplicateAdminNameException {
        if (student.getName().equals("Admin")){
            throw new DuplicateAdminNameException("Tên trùng với tên Admin");
        }
        return studentRepository.save(student)!=null;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            studentRepository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
