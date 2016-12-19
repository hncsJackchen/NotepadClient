package com.jack.notepad.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.jack.notepad.R;

/**
 * Author： Jackchen
 * Time： 2016/11/22
 * Description:欢迎界面
 */
public class WelcomeActivity extends BaseActivity implements View.OnClickListener {
    private Button mBtnLogin;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mBtnLogin = (Button) findViewById(R.id.btn_welcome_login);
        mBtnLogin.setOnClickListener(this);
        mBtnRegister = (Button) findViewById(R.id.btn_welcome_register);
        mBtnRegister.setOnClickListener(this);
        hasLogin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_welcome_login:
                login();
                break;
            case R.id.btn_welcome_register:
                register();
                break;
            default:
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * 注册
     */
    private void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到主界面
     */
    private void hasLogin() {
        boolean hasLogin = false;
        if (hasLogin) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
