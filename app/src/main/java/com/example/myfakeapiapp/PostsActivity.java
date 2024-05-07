package com.example.myfakeapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPosts;
    private JsonPlaceholderApi jsonPlaceholderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        findViewsById();
        jsonPlaceholderApi = ApiClient.getClient().create(JsonPlaceholderApi.class);
        fetchPosts();
    }

    public void findViewsById() {
        recyclerViewPosts = findViewById(R.id.recycler_view_posts);
    }


    private void fetchPosts() {
        Call<Post[]> call = jsonPlaceholderApi.getPostsArray(); // Change the return type to Post[]
        call.enqueue(new Callback<Post[]>() {

            @Override
            public void onResponse(Call<Post[]> call, Response<Post[]> response) {
                if (!response.isSuccessful()) {
                    Log.e("MainActivity", "Failed to fetch posts: " + response.code());
                    return;
                }

                Post[] postsArray = response.body(); // Retrieve the array of posts

                // Process the posts array here if needed
                // For example, update the RecyclerView adapter
                if (postsArray != null) {
                    PostsAdapter postsAdapter = new PostsAdapter(postsArray);
                    recyclerViewPosts.setAdapter(postsAdapter);
                }
            }

            @Override
            public void onFailure(Call<Post[]> call, Throwable t) {
                Log.e("MainActivity", "Failed to fetch posts: " + t.getMessage());
                // Handle the failure appropriately, e.g., show a toast message
                Toast.makeText(PostsActivity.this, "Error, couldn't get information", Toast.LENGTH_LONG).show();
            }
        });
    }
}


