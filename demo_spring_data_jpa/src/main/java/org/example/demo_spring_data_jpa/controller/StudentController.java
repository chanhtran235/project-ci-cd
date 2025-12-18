package org.example.demo_spring_data_jpa.controller;

import jakarta.validation.Valid;
import org.example.demo_spring_data_jpa.dto.StudentDto;
import org.example.demo_spring_data_jpa.entity.Student;
import org.example.demo_spring_data_jpa.exception.DuplicateNameAdminException;
import org.example.demo_spring_data_jpa.service.IStudentService;
import org.example.demo_spring_data_jpa.validate.StudentValidate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showList(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "2") int size,
            @RequestParam(name = "searchName", required = false, defaultValue = "") String searchName,
            ModelMap model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending().and(Sort.by("gender").descending()));
        Page<Student> studentPage = studentService.findAllByNameContaining(searchName, pageable);
        model.addAttribute("studentPage", studentPage);
        model.addAttribute("searchName", searchName);
        return "student/list";
    }

    @GetMapping("/add")
    public String showFormAdd(Model model) {
        model.addAttribute("studentDto", new StudentDto());
        return "student/add";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute StudentDto studentDto, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) throws DuplicateNameAdminException {
        // sử dụng cho custom validate
        StudentValidate studentValidate = new StudentValidate();
        studentValidate.validate(studentDto,bindingResult);
        // hết
        if (bindingResult.hasErrors()) {
            return "student/add";
        }
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        boolean check = studentService.save(student);
        String mess = "Không thành công";
        if (check) {
            mess = "Thành công";
        }
        redirectAttributes.addFlashAttribute("mess", mess);
        return "redirect:/students";
    }

    @GetMapping("/detail")
    public String detail1(@RequestParam(name = "id", required = false, defaultValue = "3") int id,
                          Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "/student/detail";

    }

    @GetMapping("/detail/{id}")
    public String detail2(@PathVariable(name = "id") int id,
                          Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "/student/detail";

    }
    @ExceptionHandler(DuplicateNameAdminException.class)
    public String exceptionHandle(){
        return "client-exception";
    }


}
