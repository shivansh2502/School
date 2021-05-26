package com.springrest.services;

import com.springrest.dao.mongodao.StudentMongoDao;
import com.springrest.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("mongo")
@CacheConfig(
        cacheNames = {"mongo"}
)
public class StudentServiceMongoImpl implements StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceMongoImpl.class);
    @Autowired
    private StudentMongoDao studentMongoDao;

    public StudentServiceMongoImpl() {
    }

    public List<Student> getStudents() {
        return this.studentMongoDao.findAll();
    }

    @Cacheable(
            key = "#id"
    )
    public Student getStudent(long id) {
        LOG.info("Getting Student information from Mongo for id {}", id);
        Student ans = null;
        Optional<Student> s = this.studentMongoDao.findById(id);
        if (s != null) {
            ans = (Student)s.get();
        }

        return ans;
    }

    public Student postStudent(Student stud) {
        this.studentMongoDao.save(stud);
        return stud;
    }

    @CachePut(
            key = "#stud.id"
    )
    public Student updateStudent(Student stud) {
        LOG.info("Updating Student information from Mongo for id {}", stud.getId());
        this.studentMongoDao.save(stud);
        return stud;
    }

    @CacheEvict(
            allEntries = true
    )
    public void deleteStudent(long id) {
        LOG.info("Deleting Student information from Mongo for id {}", id);
        this.studentMongoDao.deleteById(id);
    }

    public Student findByFirstname(String fname) {
        return null;
    }
}