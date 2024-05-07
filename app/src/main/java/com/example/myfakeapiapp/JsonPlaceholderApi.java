package com.example.myfakeapiapp;

import retrofit2.Call;
import retrofit2.http.GET;
public interface JsonPlaceholderApi {

    @GET("posts")
    Call<Post[]> getPostsArray();

    @GET("users")
    Call<User[]> getUsersArray();

    @GET("photos")
    Call<Photo[]> getPhotosArray();

}