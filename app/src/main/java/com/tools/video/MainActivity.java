package com.tools.video;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    public static String rtspUrl = "";
    private TextInputEditText videoUrl;
    private Button playButton;
    private CheckBox remember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //存储URL采用SharedPreference
        SharedPreferences sharedPreferences= getSharedPreferences("data",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        //获取资源
        videoUrl = findViewById(R.id.textInputEditText);
        playButton = findViewById(R.id.button);
        remember = findViewById(R.id.checkBox);

        //读取存储的URL，如不为空，则回显到输入框
        rtspUrl = sharedPreferences.getString("url", "");
        if(!rtspUrl.isEmpty()) {
            videoUrl.setText(rtspUrl);
        }

        //复选框处理函数
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, videoUrl.getText().toString(), Toast.LENGTH_SHORT).show();
                    editor.putString("url", videoUrl.getText().toString());
                }else if(!isChecked){
                    editor.remove("url");
                }
                editor.commit();
            }
        });

        //按键处理函数
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", String.valueOf(videoUrl.getText()));
                if (videoUrl.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "请输入视频流地址！", Toast.LENGTH_SHORT).show();
                } else {
                    rtspUrl = videoUrl.getText().toString();

                    //页面跳转
                    new Thread() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                            startActivity(intent);
                        }

                    }.start();
                }
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}


