package com.jack.notepad;

import android.app.Application;

import com.jack.notepad.bean.User;

/**
 * Author： Jackchen
 * Time： 2016/11/22
 * Description:应用入口
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    private User user;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //TODO something
        instance = this;
    }
    
    public static BaseApplication getInstance(){
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
