package com.jack.notepad.response;

/**
 * Created by Administrator on 2016/11/21.
 * 客户端请求的响应类
 */
public class ResponseResult {

    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_ERROR = 1;

    //返回状态 0-正常,1-异常
    private int status;
    //错误信息
    private String error_msg;
    //json格式的结果
    private Object result;

    public static ResponseResult writeResult(int status, String error_msg, Object result) {
        ResponseResult response = new ResponseResult();
        response.status = status;
        response.error_msg = error_msg;
        response.result = result;
        return response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
