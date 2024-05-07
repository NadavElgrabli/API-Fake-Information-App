package com.example.myfakeapiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {
    Photo[] photos;

    public PhotosAdapter(Photo[] photos) {
        this.photos = photos;
    }

    @Override
    public int getItemCount() {
        return photos.length;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotosViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        holder.bind(photos[position]);

    }

    static class PhotosViewHolder extends RecyclerView.ViewHolder {
        private TextView photoTitle;
        private TextView id;
        private TextView albunmID;
        private TextView photoURL;
        private ImageView imageViewPhoto;

        public PhotosViewHolder(View itemView) {
            super(itemView);
            photoTitle = itemView.findViewById(R.id.text_view_photo_title);
            id = itemView.findViewById(R.id.text_view_photo_id);
            albunmID = itemView.findViewById(R.id.text_view_album_id);
            photoURL = itemView.findViewById(R.id.text_view_photo_url);
            imageViewPhoto = itemView.findViewById(R.id.image_view_photo);
        }

        public void bind(Photo photo) {
            photoTitle.setText("Photo Title: " + photo.getTitle());
            id.setText("Photo ID: " + photo.getId());
            albunmID.setText("Album ID: " + photo.getAlbumId());
            photoURL.setText("Photo URL: " + photo.getUrl());
            Picasso.get().load(photo.getUrl()).into(imageViewPhoto);
        }

    }
}
