package org.example.student_management.dao;

import org.example.student_management.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
    List<Student> getAllStudents();
    Optional<Student> getStudentById(int id);
}
