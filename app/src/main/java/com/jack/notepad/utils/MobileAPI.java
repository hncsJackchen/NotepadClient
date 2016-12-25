package com.jack.notepad.utils;

/**
 * Author： Jackchen
 * Time： 2016/12/19
 * Description:请求服务器的接口
 */
public class MobileAPI {
    //注册
    public static final String REGISTER = Constants.HTTP_SERVER_PREFIX+"/user/register";
    //登录
    public static final String LOGIN = Constants.HTTP_SERVER_PREFIX+"/user/login";
    //添加一条记录
    public static final String ADD_NOTEPAD = Constants.HTTP_SERVER_PREFIX+"/notepad/add";
    //获取列表
    public static final String GET_NOTEPAD_LIST = Constants.HTTP_SERVER_PREFIX+"/notepad/getList";

    
}
