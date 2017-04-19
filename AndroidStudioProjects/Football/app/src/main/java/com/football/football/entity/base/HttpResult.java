package com.football.football.entity.base;

/**
 * Created by Administrator on 2016/9/7.
 */
public class HttpResult<T> {
    public int error_code;
    public String reason;

    public T result;

    public int getResultCode() {
        return error_code;
    }

    public T getData() {
        return result;
    }

}
