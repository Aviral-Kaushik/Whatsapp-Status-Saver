package com.aviral.whatsappstatussaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.aviral.whatsappstatussaver.Adapter.ShowDownloadsAdapter;
import com.aviral.whatsappstatussaver.Adapter.StatusAdapter;
import com.aviral.whatsappstatussaver.Models.DownloadedMediaModel;
import com.aviral.whatsappstatussaver.Utils.FilePath;
import com.aviral.whatsappstatussaver.Utils.RecyclerViewMargin;

import java.io.File;
import java.util.ArrayList;

public class ShowDownloadsActivity extends AppCompatActivity {

    private ArrayList<DownloadedMediaModel> mDownloadedMediaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_downloads);

        mDownloadedMediaList = new ArrayList<>();

        getDownloadedMediaFromStorage();
    }

    private void getDownloadedMediaFromStorage() {

        DownloadedMediaModel media;

        File mediaFile = FilePath.rootDirectoryForSavingStatus;

        File[] allMediaFiles = mediaFile.listFiles();

        if (allMediaFiles.length != 0) {
            for (File file : allMediaFiles) {
                if (Uri.fromFile(file).toString().endsWith(".png")
                        || Uri.fromFile(file).toString().endsWith(".jpg")
                        || Uri.fromFile(file).toString().endsWith(".mp4")) {

                    media = new DownloadedMediaModel(
                            file.getName(),
                            file.getAbsolutePath()
                    );

                    mDownloadedMediaList.add(media);

                }
            }

            setUpAdapter();
        } else {

            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(this, "Your Download List is Empty, Please download something", Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    private void setUpAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false
        );

        RecyclerView downloadsRecyclerView = findViewById(R.id.show_download_recycler_view);

        downloadsRecyclerView.setLayoutManager(linearLayoutManager);

        RecyclerViewMargin recyclerViewMargin = new RecyclerViewMargin(12);
        downloadsRecyclerView.addItemDecoration(recyclerViewMargin);

        downloadsRecyclerView.setAdapter(new ShowDownloadsAdapter(mDownloadedMediaList, this));

    }
}