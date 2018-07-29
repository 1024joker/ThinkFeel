package com.mxthd.util;

import java.io.Serializable;

/**
 * 用于封装JSON返回数据的值对象类
 */
public class JsonResult<T> implements Serializable {
    public static final int SUCCESS=0;
    public static final int ERROR=1;
    private int state;
    private String message;
    private T data;

    public JsonResult() {
    }

    public JsonResult(Exception e){
        this(ERROR, e.getMessage(), null);
    }

    public JsonResult(String errorMessage){
        this(ERROR, errorMessage, null);
    }

    public JsonResult(T data){
        this(SUCCESS, "", data);
    }

    public JsonResult(
            int state, String message) {
        this(state, message, null);
    }

    public JsonResult(
            int state, String message, T data) {

        this.state = state;
        this.message = message;
        this.data = data;
    }

    public static int getSUCCESS() {
        return SUCCESS;
    }

    public static int getERROR() {
        return ERROR;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
    }
}
