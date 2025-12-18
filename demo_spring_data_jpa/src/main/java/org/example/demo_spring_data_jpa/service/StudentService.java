package org.example.demo_spring_data_jpa.service;

import org.example.demo_spring_data_jpa.entity.Student;
import org.example.demo_spring_data_jpa.exception.DuplicateNameAdminException;
import org.example.demo_spring_data_jpa.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findAllByNameContaining(String searchName, Pageable pageable) {
        return studentRepository.findAllByNameContaining(searchName,pageable);
    }

    @Override
    public boolean save(Student student) throws DuplicateNameAdminException {
        if (student.getName().equals("Admin")){
            throw new DuplicateNameAdminException("Tên trùng với admin");
        }
        return studentRepository.save(student)!=null;
    }

    @Override
    public boolean delete(int id) {
        try{
            studentRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).orElse(null);
    }
}
