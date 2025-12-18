package org.example.demo_spring_data_jpa.repository;

import org.example.demo_spring_data_jpa.entity.ClassCG;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassRepository extends JpaRepository<ClassCG, Integer> {
}
