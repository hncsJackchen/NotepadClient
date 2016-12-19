package com.jack.notepad.activity;

import android.os.Bundle;
import android.widget.TextView;
import com.jack.notepad.R;
import com.jack.notepad.utils.CommUtils;

/**
 * Author： Jackchen
 * Time： 2016/11/22
 * Description:主界面
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvContent = (TextView) findViewById(R.id.tv_main_content);

        String data = getIntent().getStringExtra("data");
        if (CommUtils.isNotBlank(data)) {
            mTvContent.setText(data);
        }else {
            mTvContent.setText("没有有效数据");
        }
    }
}
