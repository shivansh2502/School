package com.springrest.dao.mysqldao;


import com.springrest.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentMysqlDao extends JpaRepository<Student, Long> {

}