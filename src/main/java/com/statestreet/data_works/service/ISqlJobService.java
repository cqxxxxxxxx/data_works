package com.statestreet.data_works.service;

import com.statestreet.data_works.model.dto.sqljob.SqlJobCreateDTO;
import com.statestreet.data_works.model.dto.sqljob.SqlJobStatusUpdateDTO;
import com.statestreet.data_works.model.dto.sqljob.SqlJobUpdateDTO;
import com.statestreet.data_works.model.vo.SqlJobVO;

public interface ISqlJobService {

    SqlJobVO create(SqlJobCreateDTO createDTO);

    SqlJobVO update(SqlJobUpdateDTO updateDTO);

    SqlJobVO statusUpdate(SqlJobStatusUpdateDTO updateDTO);


    void delete(Long id);
}

