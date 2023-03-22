package com.aviral.whatsappstatussaver;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class ShowVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_video);

        String videoPath = getIntent().getStringExtra(getString(R.string.video_extra));

        VideoView videoView = findViewById(R.id.videoView);

        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.requestFocus();
        videoView.start();

    }

}