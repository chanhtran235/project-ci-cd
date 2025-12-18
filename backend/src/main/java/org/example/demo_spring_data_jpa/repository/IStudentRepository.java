package org.example.demo_spring_data_jpa.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.demo_spring_data_jpa.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
 Page<Student> findAllByNameContaining(String searchName, Pageable pageable);
}
