package com.jack.notepad.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.jack.notepad.BaseApplication;

/**
 * Author： Jackchen
 * Time： 2016/11/22
 * Description:
 */
public class ToastUtils {
    private static final Handler hander = new Handler(Looper.getMainLooper());

    public static void toast(final String info) {
        hander.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseApplication.getInstance().getApplicationContext(), info,
                    Toast.LENGTH_SHORT).show();
            }
        });
    }
}
