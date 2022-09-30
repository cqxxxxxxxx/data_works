package com.statestreet.data_works.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CreateDTO extends BaseDTO {
    private Long createTime;
    private String createdBy;
}
