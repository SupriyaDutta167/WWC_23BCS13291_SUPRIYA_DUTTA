package com.example.Student.Controller;

import com.example.Student.Model.Student;
import com.example.Student.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student) {

        if (student.getName() == null || student.getName().isEmpty()
                || student.getCourse() == null || student.getCourse().isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Name and Course must not be null or empty");
        }

        if (studentService.existsById(student.getId())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Student with ID " + student.getId() + " already exists");
        }

        studentService.addStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Student registered successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {

        Student student = studentService.getStudentById(id);

        if (student == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Student with ID " + id + " not found");
        }

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        boolean deleted = studentService.deleteStudentById(id);

        if (!deleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Student with ID " + id + " not found");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Student deleted successfully");
    }
}
