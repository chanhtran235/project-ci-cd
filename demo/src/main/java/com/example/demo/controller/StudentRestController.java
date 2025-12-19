package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.exception.DuplicateAdminNameException;
import com.example.demo.service.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController()
@RequestMapping("v1/api/students")
public class StudentRestController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentList = studentService.findAll();
        if (studentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
        }
        return new ResponseEntity<>(studentList, HttpStatus.OK);//200
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody StudentDto studentDto) throws DuplicateAdminNameException {
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.CREATED);//201

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")int id){
        Student student = studentService.findById(id);
        if (student!=null){
            studentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404

    }


    @PatchMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")int id,
                                        @RequestBody StudentDto studentDto) throws DuplicateAdminNameException {
        Student student = studentService.findById(id);
        if (student!=null){
            BeanUtils.copyProperties(studentDto,student);
            studentService.save(student);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404

    }
}
