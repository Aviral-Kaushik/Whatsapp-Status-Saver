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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ImagesFragment extends Fragment {

    private ArrayList<WhatsAppStatusModel> statusList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images, container, false);

        statusList = new ArrayList<>();

        getStatusImagesFromStorage(view);

        FloatingActionButton showDownloads = view.findViewById(R.id.show_downloads);

        showDownloads.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), ShowDownloadsActivity.class)));

        return view;
    }

    private void getStatusImagesFromStorage(View view) {

        WhatsAppStatusModel statusModel;

        String statusPath = Environment.getExternalStorageDirectory().getAbsolutePath()
        +"/WhatsApp/Media/.Statuses";

        File statusDirectory = new File(statusPath);

        File[] allStatusPath = statusDirectory.listFiles();

        Arrays.sort(Objects.requireNonNull(allStatusPath), ((o1, o2) -> Long.compare(o2.lastModified(), o1.lastModified())));

        int i = 0;
        for (File file : allStatusPath) {
            if (Uri.fromFile(file).toString().endsWith(".png")
                    || Uri.fromFile(file).toString().endsWith(".jpg")) {
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

        setUpStatusAdapter(view);
    }

    private void setUpStatusAdapter(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity(),
        LinearLayoutManager.VERTICAL, false
        );

        RecyclerView imageFragmentRecyclerView = view.findViewById(R.id.image_fragment_recycler_view);

        imageFragmentRecyclerView.setLayoutManager(linearLayoutManager);

        RecyclerViewMargin recyclerViewMargin = new RecyclerViewMargin(12);
        imageFragmentRecyclerView.addItemDecoration(recyclerViewMargin);

        imageFragmentRecyclerView.setAdapter(new StatusAdapter(statusList, getContext()));
    }
}
