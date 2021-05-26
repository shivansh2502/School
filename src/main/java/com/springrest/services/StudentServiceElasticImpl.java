package com.springrest.services;

import com.springrest.dao.elasticdao.StudentElasticDao;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("elastic")
@CacheConfig(
        cacheNames = {"elastic"}
)
public class StudentServiceElasticImpl implements StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceMysqlImpl.class);
    @Autowired
    private StudentElasticDao studentElasticDao;

    public StudentServiceElasticImpl() {
    }

    public List<Student> getStudents() {
        List<Student> l = new ArrayList();
        this.studentElasticDao.findAll().forEach(l::add);
        return l;
    }

    @Cacheable(
            key = "#id"
    )
    public Student getStudent(long id) {
        LOG.info("Getting Student information from Elastic for id {}", id);
        Student ans = null;
        Optional<Student> s = this.studentElasticDao.findById(id);
        if (s != null) {
            ans = (Student)s.get();
        }

        return ans;
    }

    public Student findByFirstname(String fname) {
        return this.studentElasticDao.findByfname(fname);
    }

    @CachePut(
            key = "#stud.id"
    )
    public Student postStudent(Student stud) {
        this.studentElasticDao.save(stud);
        return stud;
    }

    public Student updateStudent(Student stud) {
        LOG.info("Updating Student information from Elastic for id {}", stud.getId());
        this.studentElasticDao.save(stud);
        return stud;
    }

    @CacheEvict(
            allEntries = true
    )
    public void deleteStudent(long id) {
        LOG.info("Deleting Student information from Elastic for id {}", id);
        this.studentElasticDao.deleteById(id);
    }
}
