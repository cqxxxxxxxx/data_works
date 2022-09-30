package com.statestreet.data_works.model.entity;

import com.statestreet.data_works.model.constant.JobStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sql_job")
public class SqlJobEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String jobId;

    @Column()
    private String content;

    private JobStatus status;


}
