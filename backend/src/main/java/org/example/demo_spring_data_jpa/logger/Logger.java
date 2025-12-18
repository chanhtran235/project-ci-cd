package org.example.demo_spring_data_jpa.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.demo_spring_data_jpa.dto.StudentDto;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    private static int viewCount = 0;

    //    @After("execution(* org.example.demo_spring_data_jpa.controller.StudentController.*(..))")
//    public void visitCountForShowList(){
//        viewCount++;
//        System.out.println("--------------------------------------------");
//        System.out.println(viewCount);
//    }
//    @AfterReturning("execution(* org.example.demo_spring_data_jpa.controller.StudentController.save(..))")
//    public void loggerAddSuccess(JoinPoint joinPoint) {
//        Object[] objects = joinPoint.getArgs();
//        StudentDto studentDto = (StudentDto) objects[0];
//
//        System.out.println("--------------------------------------------");
//        System.out.println("---------------"+studentDto.getName());
//        System.out.println("thêm mới không công");
//    }

//    @AfterThrowing("execution(* org.example.demo_spring_data_jpa.controller.StudentController.save(..))")
//    public void loggerAdException() {
//        viewCount++;
//        System.out.println("--------------------------------------------");
//        System.out.println("Thêm mới không thành công");
//    }

    @Around("execution(* org.example.demo_spring_data_jpa.controller.StudentController.save(..))")
    public Object loggerAdException(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("đoạn code chạy trước nghiệp chính");
        // nghiệp vụ chính chạy
        Object object;
        try {
            object=proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        System.out.println("đoạn code chạy sau nghiệp vụ chính");
        return object;
    }

}
