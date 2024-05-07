//package com.example.myfakeapiapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class UsersActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerViewUsers;
//    private JsonPlaceholderApi jsonPlaceholderApi;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_users);
//        findViewsById();
//        jsonPlaceholderApi = ApiClient.getClient().create(JsonPlaceholderApi.class);
//        fetchUsers();
//
//    }
//
//    public void findViewsById(){
//        recyclerViewUsers = findViewById(R.id.recycler_view_users);
//
//    }
//
//    private void fetchUsers() {
//        Call<User[]> call = jsonPlaceholderApi.getUsersArray(); // Change the return type to User[]
//        call.enqueue(new Callback<User[]>() {
//
//            @Override
//            public void onResponse(Call<User[]> call, Response<User[]> response) {
//                if (!response.isSuccessful()) {
//                    Log.e("UsersActivity", "Failed to fetch users: " + response.code());
//                    return;
//                }
//
//                User[] usersArray = response.body(); // Retrieve the array of users
//
//                // Process the posts array here if needed
//                // For example, update the RecyclerView adapter
//                if (usersArray != null) {
//                    UsersAdapter usersAdapter = new UsersAdapter(usersArray);
//                    recyclerViewUsers.setAdapter(usersAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User[]> call, Throwable t) {
//                Log.e("UsersActivity", "Failed to fetch users: " + t.getMessage());
//                // Handle the failure appropriately, e.g., show a toast message
//                Toast.makeText(UsersActivity.this, "Error, couldn't get information", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//}



package com.example.myfakeapiapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsers;
    private JsonPlaceholderApi jsonPlaceholderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        findViewsById();
        jsonPlaceholderApi = ApiClient.getClient().create(JsonPlaceholderApi.class);
        fetchUsers();
    }

    private void findViewsById() {
        recyclerViewUsers = findViewById(R.id.recycler_view_users);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchUsers() {
        Call<User[]> call = jsonPlaceholderApi.getUsersArray();
        call.enqueue(new Callback<User[]>() {
            @Override
            public void onResponse(@NonNull Call<User[]> call, @NonNull Response<User[]> response) {
                if (response.isSuccessful()) {
                    User[] usersArray = response.body();
                    if (usersArray != null) {
                        UsersAdapter usersAdapter = new UsersAdapter(usersArray, UsersActivity.this);
                        recyclerViewUsers.setAdapter(usersAdapter);
                    } else {
                        Log.e("UsersActivity", "Empty response body");
                    }
                } else {
                    Log.e("UsersActivity", "Failed to fetch users: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<User[]> call, @NonNull Throwable t) {
                Log.e("UsersActivity", "Failed to fetch users: " + t.getMessage());
                Toast.makeText(UsersActivity.this, "Error, couldn't get information", Toast.LENGTH_LONG).show();
            }
        });
    }
}
