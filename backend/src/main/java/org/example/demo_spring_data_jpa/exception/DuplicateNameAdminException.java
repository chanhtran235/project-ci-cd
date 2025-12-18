package org.example.demo_spring_data_jpa.exception;

public class DuplicateNameAdminException extends Exception{
    public DuplicateNameAdminException(String message) {
        super(message);
    }
}
