package com.aviral.whatsappstatussaver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aviral.whatsappstatussaver.Adapter.StatusAdapter;
import com.aviral.whatsappstatussaver.Models.WhatsAppStatusModel;
import com.aviral.whatsappstatussaver.Utils.FilePath;
import com.aviral.whatsappstatussaver.Utils.RecyclerViewMargin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class VideosFragment extends Fragment {

    private ArrayList<WhatsAppStatusModel> statusList;
    private RecyclerView videosFragmentRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        statusList = new ArrayList<>();

        videosFragmentRecyclerView = view.findViewById(R.id.videos_fragment_recycler_view);

        getStatusVideosFromStorage();

        FloatingActionButton showDownloads = view.findViewById(R.id.show_downloads);

        showDownloads.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), ShowDownloadsActivity.class)));

        return view;
    }

    private void getStatusVideosFromStorage() {
        WhatsAppStatusModel statusModel;

        String statusPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/WhatsApp/Media/.Statuses";

        File statusDirectory = new File(statusPath);

        File[] allStatusPath = statusDirectory.listFiles();

        Arrays.sort(Objects.requireNonNull(allStatusPath), ((o1, o2) -> Long.compare(o2.lastModified(), o1.lastModified())));

        int i = 0;
        for (File file : allStatusPath) {
            if (Uri.fromFile(file).toString().endsWith(".mp4")) {
                statusModel = new WhatsAppStatusModel(
                        "Status " + i,
                        Uri.fromFile(file).toString(),
                        file.getAbsolutePath(),
                        file.getName()
                );
                statusList.add(statusModel);
                i++;
            }
        }

        setUpStatusAdapter();
    }

    private void setUpStatusAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL, false
        );

        videosFragmentRecyclerView.setLayoutManager(linearLayoutManager);

        RecyclerViewMargin recyclerViewMargin = new RecyclerViewMargin(12);
        videosFragmentRecyclerView.addItemDecoration(recyclerViewMargin);

        videosFragmentRecyclerView.setAdapter(new StatusAdapter(statusList, getContext()));

    }
}
