package org.example.demo_spring_data_jpa.service;

import org.example.demo_spring_data_jpa.entity.ClassCG;

import java.util.List;

public interface IClassService {
    List<ClassCG> findAll();
}
