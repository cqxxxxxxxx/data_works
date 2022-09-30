package com.statestreet.data_works.model.dto.sqljob;

import com.statestreet.data_works.model.constant.JobStatus;
import com.statestreet.data_works.model.dto.CreateDTO;
import com.statestreet.data_works.model.dto.UpdateDTO;
import lombok.Data;

@Data
public class SqlJobUpdateDTO extends UpdateDTO {
    private Long id;

    private String content;

    private JobStatus status;
}
