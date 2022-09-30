package com.statestreet.data_works.model.dto.sqljob;

import com.statestreet.data_works.model.dto.BaseDTO;
import com.statestreet.data_works.model.dto.CreateDTO;
import lombok.Data;

@Data
public class SqlJobCreateDTO extends CreateDTO {

    private String content;

}
