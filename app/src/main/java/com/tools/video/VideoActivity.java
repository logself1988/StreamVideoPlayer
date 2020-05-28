package com.tools.video;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Toast.makeText(VideoActivity.this, MainActivity.rtspUrl, Toast.LENGTH_SHORT).show();
        videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        videoView.setVideoURI(Uri.parse(MainActivity.rtspUrl));
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}
