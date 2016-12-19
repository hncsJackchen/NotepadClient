package com.jack.notepad.response;

/**
 * Author： Jackchen
 * Time： 2016/11/22
 * Description:
 */
public interface HttpCallback {
    void onFailure(String cause);
    void onSuccess(ResponseResult response);
}
