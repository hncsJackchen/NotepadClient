package com.jack.notepad.utils;

/**
 * Author： Jackchen
 * Time： 2016/11/22
 * Description:
 */
public class CommUtils {

    /**
     * 判断字符串是否为空
     * @param info
     * @return
     */
    public static boolean isBlank(String info) {
        if (info == null || info.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String info) {
        return !isBlank(info);
    }
}
