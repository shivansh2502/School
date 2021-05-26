package com.springrest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@Document(
        indexName = "studentindex",
        shards = 2
)
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    @Id
    public long id;
    private String fname;
    private String lname;
    private long clas;

}
