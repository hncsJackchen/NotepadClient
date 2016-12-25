package com.jack.notepad.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.alibaba.fastjson.JSON;
import com.jack.notepad.BaseApplication;
import com.jack.notepad.R;
import com.jack.notepad.bean.User;
import com.jack.notepad.response.HttpCallback;
import com.jack.notepad.response.ResponseResult;
import com.jack.notepad.utils.CommUtils;
import com.jack.notepad.utils.MobileAPI;
import com.jack.notepad.utils.OkHttpHelper;
import com.jack.notepad.utils.ToastUtils;
import java.util.HashMap;

/**
 * Author： Jackchen
 * Time： 2016/11/22
 * Description:登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText mEtUsername;
    private EditText mEtPassword;

    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEtUsername = (EditText) findViewById(R.id.et_login_username);
        mEtPassword = (EditText) findViewById(R.id.et_login_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login_login);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                mBtnLogin.setEnabled(false);
                login();
                break;
            default:

                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        String username = mEtUsername.getText().toString();
        if (CommUtils.isBlank(username)) {
            ToastUtils.toast("账号不能为空");
            mBtnLogin.setEnabled(true);
            return;
        }
        String password = mEtPassword.getText().toString();
        if (CommUtils.isBlank(password)) {
            ToastUtils.toast("密码不能为空");
            mBtnLogin.setEnabled(true);
            return;
        }

        //参数
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", username);
        map.put("password", password);

        OkHttpHelper.httpPost(MobileAPI.LOGIN, map, new HttpCallback() {
            @Override
            public void onFailure(String cause) {
                Log.w(TAG, cause);
                ToastUtils.toast(cause);
                mBtnLogin.setEnabled(true);
            }

            @Override
            public void onSuccess(ResponseResult response) {
                if (response.getStatus() == ResponseResult.STATUS_SUCCESS) {
                    User user = JSON.parseObject(response.getResult().toString(), User.class);
                    //设置为全局变量
                    BaseApplication.getInstance().setUser(user);
                    Log.w(TAG, response.getError_msg() + " " + user);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("data", user);
                    startActivity(intent);
                    finish();
                } else {
                    Log.w(TAG, response.getError_msg());
                }
                ToastUtils.toast(response.getError_msg());
                mBtnLogin.setEnabled(true);
            }
        });
    }
}
