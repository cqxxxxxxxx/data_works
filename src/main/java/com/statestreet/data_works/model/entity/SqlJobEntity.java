package com.statestreet.data_works.model.entity;

import com.statestreet.data_works.model.constant.JobStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sql_job")
public class SqlJobEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private JobStatus status;


}
