package org.example.student_management.exception;

public class InvalidCourseException extends RuntimeException {
    public InvalidCourseException(String message) {
        super(message);
    }
}
