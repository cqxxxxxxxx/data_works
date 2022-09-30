package com.statestreet.data_works.model;

import com.statestreet.data_works.exception.ErrorType;
import lombok.Data;

@Data
public class Result<T> {
    private T data;
    private String code;
    private String msg;

    public static <R> Result<R> success(R r) {
        Result<R> result = new Result<>();
        result.setCode(ErrorType.SUCCESS.getCode());
        result.setMsg(ErrorType.SUCCESS.getMsg());
        return result;
    }

}
