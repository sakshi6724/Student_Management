package org.example.student_management.dao;

import org.example.student_management.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository("studentDAO")  // Use a qualifier name here
public class StudentDAOImpl implements StudentDAO {

    private static final List<Student> students = new ArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    static {
        // Initialize some static students
        Student student1 = new Student();
        student1.setId(idCounter.getAndIncrement());
        student1.setName("Juju");
        student1.setEmail("juju@example.com");
        student1.setCourse("Maths");
        students.add(student1);

        Student student2 = new Student();
        student2.setId(idCounter.getAndIncrement());
        student2.setName("Jini");
        student2.setEmail("jini@example.com");
        student2.setCourse("Computer Science");
        students.add(student2);
    }

    @Override
    public void addStudent(Student student) {
        student.setId(idCounter.getAndIncrement());
        students.add(student);
    }

    @Override
    public void updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                return;
            }
        }
    }

    @Override
    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        return students.stream().filter(student -> student.getId() == id).findFirst();
    }
}
