package org.example.demo_spring_data_jpa.service;

import org.example.demo_spring_data_jpa.entity.ClassCG;
import org.example.demo_spring_data_jpa.repository.IClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassService implements IClassService {
    @Autowired
    private IClassRepository classRepository;
    @Override
    public List<ClassCG> findAll() {
        return classRepository.findAll();
    }
}
