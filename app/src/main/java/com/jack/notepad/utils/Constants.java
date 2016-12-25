package com.jack.notepad.utils;

/**
 * Author： Jackchen
 * Time： 2016/12/7
 * Description:
 */
public class Constants {
    public static final boolean IS_DEV = false;

    //开发环境
    public static final String IP_DEV = "localhost";

    //生产环境
    public static final String IP_PROD = "23.83.233.171";


    public static final String PROTOCOL = "http://";
    public static final String IP = IS_DEV ? IP_DEV : IP_PROD;
    public static final int PORT = 8080;
    //服务器的地址
    public static final String HTTP_SERVER_PREFIX = PROTOCOL + IP + ":" + PORT;


}
