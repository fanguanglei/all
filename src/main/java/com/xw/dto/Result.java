package com.xw.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

public class Result <T>{

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> data;

    public Result(Integer code, String name) {
        this.code = code;
        this.msg = name;
    }

    public Result(Integer code, List<T> data) {
        this.code = code;
        this.data = data;
    }

    public Result() {
    }

    public Result<Void>  error(Integer code,String msg){
        Result<Void> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }




}


