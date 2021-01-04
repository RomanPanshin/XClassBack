package com.Xjournal.Group.Entity;


public class Result<T> {
    public enum ResultEnum{
        Success,
        Error
    }

    private ResultEnum code;
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ResultEnum getCode() {
        return code;
    }

    public void setCode(ResultEnum code) {
        this.code = code;
    }
    public Result()
    {
    }
    public Result(ResultEnum code, T result)
    {
        this.code = code;
        this.result = result;
    }
}
