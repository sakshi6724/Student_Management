package org.example.student_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class exception {

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<String> InvalidNameException(InvalidNameException ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidCourseException.class)
    public ResponseEntity<String> InvalidCourseException(InvalidCourseException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> InvalidEmailException(InvalidEmailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}
