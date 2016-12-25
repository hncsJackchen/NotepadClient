package com.jack.notepad.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.jack.notepad.BaseApplication;
import com.jack.notepad.R;
import com.jack.notepad.bean.Notepad;
import com.jack.notepad.bean.User;
import com.jack.notepad.response.HttpCallback;
import com.jack.notepad.response.ResponseResult;
import com.jack.notepad.utils.CommUtils;
import com.jack.notepad.utils.MobileAPI;
import com.jack.notepad.utils.OkHttpHelper;
import com.jack.notepad.utils.ToastUtils;

import java.util.HashMap;

public class EditNotepadActivity extends AppCompatActivity {
    private static final String TAG = "EditNotepadActivity";
    private EditText mEtTitle;
    private EditText mEtContent;
    private Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notepad);

        mEtTitle = (EditText) findViewById(R.id.et_edit_notepad_title);
        mEtContent = (EditText) findViewById(R.id.et_edit_notepad_content);
        mBtnSubmit = (Button) findViewById(R.id.btn_edit_notepad_submit);
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit() {
        User user = BaseApplication.getInstance().getUser();
        if (user == null) {
            ToastUtils.toast("请先登录");
            return;
        }

        String title = mEtTitle.getText().toString();
        if (CommUtils.isBlank(title)) {
            ToastUtils.toast("标题不能为空");
            return;
        }

        String content = mEtContent.getText().toString();
        if (CommUtils.isBlank(content)) {
            ToastUtils.toast("内容不能为空");
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("uid", ""+user.getUid());
        map.put("title", title);
        map.put("content", content);

        OkHttpHelper.httpPost(MobileAPI.ADD_NOTEPAD, map, new HttpCallback() {
            @Override
            public void onFailure(String cause) {
                Log.e(TAG, cause);
                ToastUtils.toast(cause);
            }

            @Override
            public void onSuccess(ResponseResult response) {
                if (response.getStatus() == ResponseResult.STATUS_SUCCESS) {
                    Notepad notepad = JSON.parseObject(response.getResult().toString(), Notepad.class);
                    Log.i(TAG, "添加成功："+notepad);
                    setResult(RESULT_OK);
                    finish();
                }else {
                    Log.w(TAG, response.getError_msg());
                }
            }
        });


    }
}
