package org.example.demo_spring_data_jpa.controller;


import org.example.demo_spring_data_jpa.dto.StudentDto;
import org.example.demo_spring_data_jpa.entity.Student;
import org.example.demo_spring_data_jpa.exception.DuplicateNameAdminException;
import org.example.demo_spring_data_jpa.service.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("v1/api/students")
public class RestStudentController {
    @Autowired
    private IStudentService studentService;

    //lấy danh sách
    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        List<Student> studentList = studentService.findAll();
        if (studentList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 ( thành công không trả về dữ lieu;
        }
        return new ResponseEntity<>(studentList,HttpStatus.OK); // 200
    }
//    @GetMapping
//    public ResponseEntity<Page<Student>> getAll(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "searchName", required = false, defaultValue = "") String searchName,
//            ModelMap model) {
//        Pageable pageable = PageRequest.of(page, 2);
//        Page<Student> studentPage = studentService.findAllByNameContaining(searchName, pageable);
//        if (studentPage.getTotalPages() == 0) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(studentPage, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 (lỗi không tìm thấy dữ liệu);
        }
        return new ResponseEntity<>(student, HttpStatus.OK); // 200
    }

    // thêm mới
    @PostMapping
    public ResponseEntity<?> add(@RequestBody StudentDto studentDto) throws DuplicateNameAdminException {
        // kiểm trả validate
        // + nếu không ok thì lấy thông tin error => bỏ map => trả về client => 400;
        // + nếu ok thì tiếp bên dưới
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteById(@PathVariable("id") int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 (lỗi không tìm thấy dữ liệu);
        }
        // gọi method xoá để xoá
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> deleteById(@PathVariable("id") int id,
                                              @RequestBody StudentDto studentDto) throws DuplicateNameAdminException {
        Student student = studentService.findById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 (lỗi không tìm thấy dữ liệu);
        }
        BeanUtils.copyProperties(studentDto, student);
        studentService.save(student);
        // gọi method xoá để xoá
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

}
