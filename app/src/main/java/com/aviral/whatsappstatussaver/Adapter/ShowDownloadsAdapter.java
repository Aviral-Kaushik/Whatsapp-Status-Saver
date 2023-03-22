package com.aviral.whatsappstatussaver.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aviral.whatsappstatussaver.Models.DownloadedMediaModel;
import com.aviral.whatsappstatussaver.Models.WhatsAppStatusModel;
import com.aviral.whatsappstatussaver.R;
import com.aviral.whatsappstatussaver.ShowImageActivity;
import com.aviral.whatsappstatussaver.ShowVideoActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ShowDownloadsAdapter extends RecyclerView.Adapter<ShowDownloadsAdapter.ViewHolder> {

    private final ArrayList<DownloadedMediaModel> downloadedMediaList;
    private final Context context;

    public ShowDownloadsAdapter(ArrayList<DownloadedMediaModel> downloadedMediaList, Context context) {
        this.downloadedMediaList = downloadedMediaList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.snippet_layout_show_download_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (downloadedMediaList.get(position).getName().length() > 23) {
            holder.mediaName.setText(downloadedMediaList.get(position).getName().substring(0, 20) + "....");
        } else {
            holder.mediaName.setText(downloadedMediaList.get(position).getName());

        }

        Glide.with(context)
                .load(downloadedMediaList.get(position).getPath())
                .into(holder.mediaImage);

        holder.itemView.setOnClickListener(view -> {
            Intent intent;
            if (downloadedMediaList.get(position).getPath().endsWith(".mp4")) {

                intent = new Intent(context, ShowVideoActivity.class);
                intent.putExtra(context.getString(R.string.video_extra), downloadedMediaList.get(position).getPath());

            } else {

                intent = new Intent(context, ShowImageActivity.class);
                intent.putExtra(context.getString(R.string.image_extra), downloadedMediaList.get(position).getPath());

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
        return downloadedMediaList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mediaImage;
        private TextView mediaName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mediaImage = itemView.findViewById(R.id.media_image);
            mediaName = itemView.findViewById(R.id.media_name);

        }
    }

}
