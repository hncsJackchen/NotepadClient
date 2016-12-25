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
 * Description: 注册界面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    private EditText mEtUsername;
    private EditText mEtPassword;
    private EditText mEtPassword2;

    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEtUsername = (EditText) findViewById(R.id.et_register_username);
        mEtPassword = (EditText) findViewById(R.id.et_register_password);
        mEtPassword2 = (EditText) findViewById(R.id.et_register_password2);

        mBtnRegister = (Button) findViewById(R.id.btn_register_register);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_register:
                mBtnRegister.setEnabled(false);
                register();
                break;
            default:
                break;
        }
    }

    /**
     * 注册
     */
    private void register() {
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        String password2 = mEtPassword2.getText().toString();

        if (CommUtils.isBlank(username)) {
            ToastUtils.toast("用户名不能为空");
            mBtnRegister.setEnabled(true);
            return;
        }

        if (CommUtils.isBlank(password)) {
            ToastUtils.toast("请设置密码");
            mBtnRegister.setEnabled(true);
            return;
        }

        if (CommUtils.isBlank(password2)) {
            ToastUtils.toast("请确认密码");
            mBtnRegister.setEnabled(true);
            return;
        }

        if (!password.equals(password2)) {
            ToastUtils.toast("两次输入的密码不一致");
            mBtnRegister.setEnabled(true);
            return;
        }

        //参数
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", username);
        map.put("password", password);

        OkHttpHelper.httpPost(MobileAPI.REGISTER, map, new HttpCallback() {
            @Override
            public void onFailure(String cause) {
                Log.w(TAG, cause);
                ToastUtils.toast(cause);
                mBtnRegister.setEnabled(true);
            }

            @Override
            public void onSuccess(ResponseResult response) {
                if (response.getStatus() == ResponseResult.STATUS_SUCCESS) {
                    User user = JSON.parseObject(response.getResult().toString(), User.class);
                    //设置为全局变量
                    BaseApplication.getInstance().setUser(user);
                    Log.w(TAG, response.getError_msg() + " " + user);
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.putExtra("data", user);
                    startActivity(intent);
                    finish();
                } else {
                    Log.w(TAG, response.getError_msg());
                }
                ToastUtils.toast(response.getError_msg());
                mBtnRegister.setEnabled(true);
            }
        });
    }
}
