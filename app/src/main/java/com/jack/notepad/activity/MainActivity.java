package com.jack.notepad.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jack.notepad.BaseApplication;
import com.jack.notepad.R;
import com.jack.notepad.adapter.NotepadAdapter;
import com.jack.notepad.bean.Notepad;
import com.jack.notepad.bean.User;
import com.jack.notepad.response.HttpCallback;
import com.jack.notepad.response.ResponseResult;
import com.jack.notepad.utils.MobileAPI;
import com.jack.notepad.utils.OkHttpHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Author： Jackchen
 * Time： 2016/11/22
 * Description:主界面
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private User mUser;
    private TextView mTvNodata;
    private ImageView mIvEdit;
    private ListView mLvList;
    private List<Notepad> mData;
    private NotepadAdapter mNotepadAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUser = (User) getIntent().getSerializableExtra("data");
        mTvNodata = (TextView) findViewById(R.id.tv_main_no_data);
        mTvNodata.setVisibility(View.GONE);

        mIvEdit = (ImageView) findViewById(R.id.iv_main_edit);
        mIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditNotepadActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        mLvList = (ListView) findViewById(R.id.lv_main_list);
        mLvList.setVisibility(View.GONE);
        mData = new ArrayList<>();
        mNotepadAdapter = new NotepadAdapter(this, mData);
        mLvList.setAdapter(mNotepadAdapter);
        loadData();
    }

    private void loadData() {
        Log.i(TAG, "loadData()");
        User user = BaseApplication.getInstance().getUser();
        if (user == null) {
            showErrorMsg("请先登录");
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "" + user.getUid());
        OkHttpHelper.httpPost(MobileAPI.GET_NOTEPAD_LIST, map, new HttpCallback() {
            @Override
            public void onFailure(String cause) {
                showErrorMsg("获取数据异常："+cause);
            }

            @Override
            public void onSuccess(ResponseResult response) {
                if (response.getStatus() == ResponseResult.STATUS_SUCCESS) {
                    Log.i(TAG, "请求成功："+JSON.toJSONString(response.getResult()));
                    List<Notepad> list = JSON.parseArray(response.getResult().toString(), Notepad.class);
                    mData.clear();
                    if (list != null && list.size() > 0) {
                        for (Notepad notepad : list) {
                            mData.add(notepad);
                        }
                        Collections.sort(mData);
                        mLvList.setVisibility(View.VISIBLE);
                        mTvNodata.setVisibility(View.GONE);
                        mNotepadAdapter.notifyDataSetChanged();
                    }else {
                        mLvList.setVisibility(View.GONE);
                        mTvNodata.setVisibility(View.VISIBLE);
                        showErrorMsg("没有数据哦");
                    }
                }else {
                    showErrorMsg("请求失败" + response.getError_msg());
                }
            }
        });
    }

    private void showErrorMsg(String info){
        mTvNodata.setVisibility(View.VISIBLE);
        mTvNodata.setText(info);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                loadData();
            }
        }
    }
}
