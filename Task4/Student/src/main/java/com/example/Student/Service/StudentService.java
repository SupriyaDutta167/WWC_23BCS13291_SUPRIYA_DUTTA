package com.example.Student.Service;

import java.util.ArrayList;
import java.util.List;
import com.example.Student.Model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    // check if student exists by id
    public boolean existsById(int id) {
        return getStudentById(id) != null;
    }

    // add student
    public void addStudent(Student student) {
        students.add(student);
    }

    // get all students
    public List<Student> getAllStudent() {
        return students;
    }

    // get student by id
    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // delete student by id
    public boolean deleteStudentById(int id) {
        Student student = getStudentById(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }
}
