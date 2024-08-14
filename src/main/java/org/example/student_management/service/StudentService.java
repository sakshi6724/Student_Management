package org.example.student_management.service;

import org.example.student_management.dao.StudentDAO;
import org.example.student_management.exception.InvalidNameException;
import org.example.student_management.model.Student;
import org.example.student_management.exception.InvalidCourseException;
import org.example.student_management.exception.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class StudentService {

    private final StudentDAO studentDAO;

    private static final Set<String> Options = new HashSet<>();

    static {
        Options.add("Mathematics");
        Options.add("Science");
        Options.add("English");
    }

    @Autowired
    public StudentService(@Qualifier("studentDAO") StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void addStudent(Student student) {
        validateStudent(student);
        studentDAO.addStudent(student);
    }

    public void updateStudent(Student student) {
        validateStudent(student);
        studentDAO.updateStudent(student);
    }

    public boolean partiallyUpdateStudent(int id, Student student) {
        Optional<Student> existingStudentOpt = studentDAO.getStudentById(id);
        if (existingStudentOpt.isPresent()) {
            Student existingStudent = existingStudentOpt.get();

            if (student.getName() != null && !student.getName().isEmpty()) {
                if (!isValidName(student.getName())) {
                    throw new InvalidNameException("Invalid name: " + student.getName() + " Name must be not empty.");
                }
                existingStudent.setName(student.getName());
            }
            if (student.getCourse() != null) {
                if (!isValidCourse(student.getCourse())) {
                    throw new InvalidCourseException("Invalid course: " + student.getCourse() + " Course must be valid.");
                }
                existingStudent.setCourse(student.getCourse());
            }
            if (student.getEmail() != null) {
                if (!isValidEmail(student.getEmail())) {
                    throw new InvalidEmailException("Invalid email: " + student.getEmail() + " Email format is incorrect.");
                }
                existingStudent.setEmail(student.getEmail());
            }

            studentDAO.updateStudent(existingStudent);
            return true;
        } else {
            return false;
        }
    }

    public void deleteStudent(int id) {
        studentDAO.deleteStudent(id);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Optional<Student> getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    private void validateStudent(Student student) {
        if (!isValidName(student.getName())) {
            throw new InvalidNameException("Invalid name: " + student.getName() + " Name must be not empty.");
        }
        if (!isValidCourse(student.getCourse())) {
            throw new InvalidCourseException("Invalid course: " + student.getCourse() + " Course must be valid.");
        }
        if (!isValidEmail(student.getEmail())) {
            throw new InvalidEmailException("Invalid email: " + student.getEmail() + " Email format is incorrect.");
        }
    }
    private boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }
    private boolean isValidCourse(String course) {
        return course != null && Options.contains(course.trim());
    }

    private boolean isValidEmail(String email) {
        return email != null && Pattern.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+$", email);
    }
}
