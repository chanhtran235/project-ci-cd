package com.example.demo.validate;

import com.example.demo.dto.StudentDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidate implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentDto studentDto = (StudentDto) target;
        if ("".equals(studentDto.getName())){
            errors.rejectValue("name","notEmpty","Ten khong dc trong");
        }else if (!studentDto.getName().matches("^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$")){
            errors.rejectValue("name",null,"Ten sai định dạng");

        }
    }
}
