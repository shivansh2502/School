package com.springrest.dao.elasticdao;


import com.springrest.entities.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StudentElasticDao extends ElasticsearchRepository<Student, Long> {
    Student findByfname(String fname);
}
