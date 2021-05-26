package com.springrest.dao.mongodao;

import com.springrest.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentMongoDao extends MongoRepository<Student, Long> {

}
