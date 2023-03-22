package com.aviral.whatsappstatussaver.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.aviral.whatsappstatussaver.Models.WhatsAppStatusModel;
import com.aviral.whatsappstatussaver.R;
import com.aviral.whatsappstatussaver.ShowImageActivity;
import com.aviral.whatsappstatussaver.ShowVideoActivity;
import com.aviral.whatsappstatussaver.SuccessfulActivity;
import com.aviral.whatsappstatussaver.Utils.FilePath;
import com.bumptech.glide.Glide;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {

    private static final String TAG = "StatusAdapter";

    private final ArrayList<WhatsAppStatusModel> statusList;
    private final Context context;
    private final String saveFilePath = FilePath.rootDirectoryForSavingStatus + "/";

    public StatusAdapter(ArrayList<WhatsAppStatusModel> statusList, Context context) {
        this.statusList = statusList;
        this.context = context;
    }

    @NonNull
    @Override
    public StatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.snippet_layout_status_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusAdapter.ViewHolder holder, int position) {

        Glide.with(context)
                .load(statusList.get(position).getPath())
                .into(holder.statusImageView);

        holder.statusDownloadButton.setOnClickListener(view -> {
            FilePath.createDirectory();
            final File file = new File(statusList.get(position).getPath());
            File fileStoringPath = new File(saveFilePath);

            try {
                FileUtils.copyFileToDirectory(file, fileStoringPath);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "onBindViewHolder: Error Occurred While Storing FIle " + e.getMessage());
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }

            context.startActivity(new Intent(context, SuccessfulActivity.class));

        });

        holder.statusViewButton.setOnClickListener(view -> {

            Intent intent;
            if (statusList.get(position).getPath().endsWith(".mp4")) {

                intent = new Intent(context, ShowVideoActivity.class);
                intent.putExtra(context.getString(R.string.video_extra), statusList.get(position).getPath());

            } else {

                intent = new Intent(context, ShowImageActivity.class);
                intent.putExtra(context.getString(R.string.image_extra), statusList.get(position).getPath());

            }
            context.startActivity(intent);
        });

        setAnimation(holder.itemView, context);
    }

    private void setAnimation(View itemView, Context context) {

        Animation animation = AnimationUtils.loadAnimation(context,
                android.R.anim.slide_in_left);

        itemView.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView statusImageView;
        private AppCompatButton statusDownloadButton;
        private AppCompatButton statusViewButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            statusImageView = itemView.findViewById(R.id.status_image);
            statusDownloadButton = itemView.findViewById(R.id.download_status);
            statusViewButton = itemView.findViewById(R.id.view_status);
        }
    }
}
