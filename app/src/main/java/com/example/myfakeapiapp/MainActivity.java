package com.example.myfakeapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView card_view_posts;
    CardView card_view_photos;
    CardView card_view_users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsByID();
        setOnClickListeners();


    }



    public void findViewsByID() {
        card_view_posts = findViewById(R.id.card_view_posts);
        card_view_photos = findViewById(R.id.card_view_photos);
        card_view_users = findViewById(R.id.card_view_users);
    }

    public void setOnClickListeners() {
        card_view_posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent postsIntent = new Intent(MainActivity.this, PostsActivity.class);
                startActivity(postsIntent);
            }
        });

        card_view_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photosIntent = new Intent(MainActivity.this, PhotosActivity.class);
                startActivity(photosIntent);
            }
        });

        card_view_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usersIntent = new Intent(MainActivity.this, UsersActivity.class);
                startActivity(usersIntent);
                    }
        });
    }
}