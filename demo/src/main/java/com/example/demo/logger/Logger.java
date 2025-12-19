package com.example.demo.logger;

import com.example.demo.dto.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class Logger {
    private static int count=0;

//    @After("execution(* com.example.demo.controller.StudentController.*(..))")
//    public void countVisit(){
//        count++;
//        System.out.println("----------------------");
//        System.out.println(count);
//    }
//    @AfterReturning("execution(* com.example.demo.controller.StudentController.save(..))")
//    public void logAddSuccess(JoinPoint joinPoint){
//        Object[] args = joinPoint.getArgs();
//        StudentDto studentDto = (StudentDto)args[0];
//        System.out.println("---------Thêm mới thành công -------------");
//        System.out.println("++++++++++ "+ studentDto.getName()+ "++++++++++++++++++");
//    }
//    @AfterThrowing("execution(* com.example.demo.controller.StudentController.save(..))")
//    public void logAddThrowing(){
//        System.out.println("-------Khong thanh cong---------------");
//    }

    @Around("execution(* com.example.demo.controller.StudentController.save(..))")
    public Object logAddThrowing(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("-------Chạy  trước nghiệp vụ chính---------------");
        Object object;
        try {
          object=  proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        System.out.println("-------Chạy sau nghiệp vụ chính---------------");
        return object;
    }
}
