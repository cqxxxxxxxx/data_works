package com.statestreet.data_works.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class UpdateDTO extends BaseDTO {
    private Long updateTime;
    private String updatedBy;
}
