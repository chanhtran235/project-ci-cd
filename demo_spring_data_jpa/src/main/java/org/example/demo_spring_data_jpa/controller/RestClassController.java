package org.example.demo_spring_data_jpa.controller;

import org.example.demo_spring_data_jpa.entity.ClassCG;
import org.example.demo_spring_data_jpa.entity.Student;
import org.example.demo_spring_data_jpa.service.IClassService;
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

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/api/classes")
public class RestClassController {
    @Autowired
    private IClassService classService;
    @GetMapping
    public ResponseEntity<List<ClassCG>> showList() {

        return new ResponseEntity<>(classService.findAll(), HttpStatus.OK);
    }
}
