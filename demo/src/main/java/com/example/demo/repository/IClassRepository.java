package com.example.demo.repository;

import com.example.demo.dto.ClassDTResponse;
import com.example.demo.entity.ClassCG;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClassRepository extends JpaRepository<ClassCG, Integer> {
    @Query(value = "select c.id, c.name as className, count(s.id) as studentQuantity from student s\n" +
            "join classcg c on s.class_id = c.id group by c.id",nativeQuery = true)
  List<ClassDTResponse> getAllClass();
}
