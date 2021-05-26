package com.springrest.controller;

import com.springrest.entities.Student;
import com.springrest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/mysql"})
public class MysqlController {
    @Autowired
    @Qualifier("mysql")
    private StudentService studentService;

    public MysqlController() {
    }

    @GetMapping({"/students"})
    public List<Student> getStudents() {
        return this.studentService.getStudents();
    }

    @GetMapping({"/students/{studentID}"})
    public Student getStudent(@PathVariable String studentID) {
        return this.studentService.getStudent(Long.parseLong(studentID));
    }

    @PostMapping({"/students"})
    public Student postStudent(@RequestBody Student stud) {
        return this.studentService.postStudent(stud);
    }

    @PutMapping({"/students"})
    public Student updateStudent(@RequestBody Student stud) {
        return this.studentService.updateStudent(stud);
    }

    @DeleteMapping({"/students/{StudentId}"})
    public ResponseEntity<HttpStatus> DeleteStudent(@PathVariable String StudentId) {
        try {
            this.studentService.deleteStudent(Long.parseLong(StudentId));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception var3) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
