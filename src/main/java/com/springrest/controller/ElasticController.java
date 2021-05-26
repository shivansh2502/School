//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.springrest.controller;

import com.springrest.entities.Student;
import java.util.List;

import com.springrest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/elastic"})
public class ElasticController {

    @Autowired
    @Qualifier("elastic")
    private StudentService studentService;

    public ElasticController() {
    }

    @GetMapping({"/students"})
    public List<Student> getStudents() {
        return this.studentService.getStudents();
    }

    @GetMapping({"/students/id/{studentID}"})
    public Student getStudent(@PathVariable String studentID) {
        return this.studentService.getStudent(Long.parseLong(studentID));
    }

    @GetMapping({"/students/fname/{studentfname}"})
    public Student findByFirstname(@PathVariable String studentfname) {
        return this.studentService.findByFirstname(studentfname);
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
