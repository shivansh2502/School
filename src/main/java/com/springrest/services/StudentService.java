//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.springrest.services;

import com.springrest.entities.Student;
import java.util.List;

public interface StudentService {
    List<Student> getStudents();

    Student getStudent(long id);

    Student postStudent(Student stud);

    Student updateStudent(Student stud);

    void deleteStudent(long id);

    Student findByFirstname(String fname);
}
