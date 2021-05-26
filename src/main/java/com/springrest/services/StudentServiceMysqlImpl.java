//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.springrest.services;

import com.springrest.dao.mysqldao.StudentMysqlDao;
import com.springrest.entities.Student;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Qualifier("mysql")
@CacheConfig(
        cacheNames = {"mysql"}
)
public class StudentServiceMysqlImpl implements StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceMysqlImpl.class);

    @Autowired
    private StudentMysqlDao studentMysqlDao;

    public StudentServiceMysqlImpl() {
    }

    public List<Student> getStudents() {
        return this.studentMysqlDao.findAll();
    }

    @Cacheable(
            key = "#id"
    )
    public Student getStudent(long id) {
        LOG.info("Getting Student information from Mysql for id {}", id);
        Student ans = null;
        Optional<Student> s = this.studentMysqlDao.findById(id);
        if (s != null) {
            ans = (Student)s.get();
        }

        return ans;
    }

    public Student postStudent(Student stud) {
        this.studentMysqlDao.save(stud);
        return stud;
    }

    @CachePut(
            key = "#stud.id"
    )
    public Student updateStudent(Student stud) {
        LOG.info("Updating Student information from Mysql for id {}", stud.getId());
        this.studentMysqlDao.save(stud);
        return stud;
    }

    @CacheEvict(
            allEntries = true
    )
    public void deleteStudent(long id) {
        LOG.info("Deleting Student information from Mysql for id {}", id);
        Student entity = (Student)this.studentMysqlDao.getOne(id);
        this.studentMysqlDao.delete(entity);
    }

    public Student findByFirstname(String fname) {
        return null;
    }
}
