package com.statestreet.data_works.model.dto.sqljob;

import com.statestreet.data_works.model.constant.JobStatus;
import com.statestreet.data_works.model.dto.UpdateDTO;
import lombok.Data;

@Data
public class SqlJobStatusUpdateDTO extends UpdateDTO {

    private Long id;

    private JobStatus status;
}
