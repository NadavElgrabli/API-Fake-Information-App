package com.example.myfakeapiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    Post[] posts;

    public PostsAdapter(Post[] posts) {
        this.posts = posts;
    }

    @Override
    public int getItemCount() {
        return posts.length;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.bind(posts[position]);

    }

    static class PostsViewHolder extends RecyclerView.ViewHolder {
        private TextView userID;
        private TextView postID;
        private TextView postTitle;
        private TextView postBody;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            userID = itemView.findViewById(R.id.text_view_post_user_id);
            postID = itemView.findViewById(R.id.text_view_post_id);
            postTitle = itemView.findViewById(R.id.text_view_post_title);
            postBody = itemView.findViewById(R.id.text_view_post_body);
        }

        public void bind(Post post) {
            postTitle.setText("Title: " + post.getTitle());
            postBody.setText("Descripton: " + post.getBody());
            postID.setText("Post ID: " + String.valueOf(post.getId()));
            userID.setText("User ID: " + String.valueOf(post.getUserId()));
        }


    }
}
