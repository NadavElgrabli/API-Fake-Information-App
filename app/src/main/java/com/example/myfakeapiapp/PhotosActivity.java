// FIRST VERSION
//package com.example.myfakeapiapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.util.Arrays;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class PhotosActivity extends AppCompatActivity {
//    private RecyclerView recyclerViewPhotos;
//    private JsonPlaceholderApi jsonPlaceholderApi;
//    private EditText editTextPhotoId;
//    private Button buttonFetchPhoto;
//    private Button buttonBack;
//    private Photo[] filteredPhotos;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_photos);
//        findViewsById();
//        setOnClickListeners();
//        jsonPlaceholderApi = ApiClient.getClient().create(JsonPlaceholderApi.class);
//        fetchPhotos();
//
//
//    }
//
//    public void findViewsById() {
//        recyclerViewPhotos = findViewById(R.id.recycler_view_photos);
//        editTextPhotoId = findViewById(R.id.edit_text_photo_id);
//        buttonFetchPhoto = findViewById(R.id.button_get_photo);
//        buttonBack = findViewById(R.id.button_back);
//    }
//
//    public void setOnClickListeners() {
//        buttonFetchPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Re-filter photos based on entered ID
//                fetchPhotos(); // This will call the modified fetchPhotos method
//            }
//        });
//
//        buttonBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PhotosActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void fetchPhotos() {
//        Call<Photo[]> call = jsonPlaceholderApi.getPhotosArray();
//        call.enqueue(new Callback<Photo[]>() {
//
//            @Override
//            public void onResponse(Call<Photo[]> call, Response<Photo[]> response) {
//                if (!response.isSuccessful()) {
//                    Log.e("MainActivity", "Failed to fetch photos: " + response.code());
//                    return;
//                }
//
//                Photo[] photosArray = response.body();
//
//                if (photosArray != null) {
//                    final String enteredId = editTextPhotoId.getText().toString().trim();
//                    if (!enteredId.isEmpty()) {
//                        filteredPhotos = Arrays.stream(photosArray).filter(photo -> String.valueOf(photo.getId()).equals(enteredId)).toArray(Photo[]::new);
//                    } else {
//                        filteredPhotos = photosArray; // Show all photos if input is empty
//                    }
//                    PhotosAdapter photosAdapter = new PhotosAdapter(filteredPhotos);
//                    recyclerViewPhotos.setAdapter(photosAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Photo[]> call, Throwable t) {
//                Log.e("MainActivity", "Failed to fetch photos: " + t.getMessage());
//                Toast.makeText(PhotosActivity.this, "Error, couldn't get information", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//}
///////////////////////////////////////////////////////////////////////


//THIRD DOESNT WORK WELL
//package com.example.myfakeapiapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.util.Arrays;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class PhotosActivity extends AppCompatActivity {
//    private RecyclerView recyclerViewPhotos;
//    private JsonPlaceholderApi jsonPlaceholderApi;
//    private EditText editTextPhotoId;
//    private EditText editTextFilterAlbumId;
//    private EditText editTextPhotoTitle;
//    private Button buttonFetchPhotoById;
//    private Button buttonFetchPhotoByTitle;
//    private Button buttonFilterAlbum;
//    private Button buttonBack;
//    private Photo[] allPhotos;
//    private Photo[] filteredPhotos;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_photos);
//        findViewsById();
//        setOnClickListeners();
//        jsonPlaceholderApi = ApiClient.getClient().create(JsonPlaceholderApi.class);
//        fetchAllPhotos();
//    }
//
//    public void findViewsById() {
//        recyclerViewPhotos = findViewById(R.id.recycler_view_photos);
//        editTextPhotoId = findViewById(R.id.edit_text_photo_id);
//        editTextFilterAlbumId = findViewById(R.id.edit_text_filter_album_id);
//        editTextPhotoTitle = findViewById(R.id.edit_text_photo_title);
//        buttonFetchPhotoById = findViewById(R.id.button_get_photo_by_id);
//        buttonFetchPhotoByTitle = findViewById(R.id.button_get_photo_by_title);
//        buttonFilterAlbum = findViewById(R.id.button_filter_album);
//        buttonBack = findViewById(R.id.button_back);
//    }
//
//    public void setOnClickListeners() {
//        buttonFetchPhotoById.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String enteredId = editTextPhotoId.getText().toString().trim();
//                fetchPhotosById(enteredId);
//            }
//        });
//
//        buttonFetchPhotoByTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String enteredTitle = editTextPhotoTitle.getText().toString().trim();
//                fetchPhotosByTitle(enteredTitle);
//            }
//        });
//
//        buttonFilterAlbum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String enteredAlbumId = editTextFilterAlbumId.getText().toString().trim();
//                filterPhotosByAlbumId(enteredAlbumId);
//            }
//        });
//
//        buttonBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PhotosActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void fetchAllPhotos() {
//        Call<Photo[]> call = jsonPlaceholderApi.getPhotosArray();
//        call.enqueue(new Callback<Photo[]>() {
//            @Override
//            public void onResponse(Call<Photo[]> call, Response<Photo[]> response) {
//                if (!response.isSuccessful()) {
//                    Log.e("PhotosActivity", "Failed to fetch photos: " + response.code());
//                    return;
//                }
//
//                allPhotos = response.body();
//                filteredPhotos = allPhotos; // Initially, filtered photos are all photos
//                updateRecyclerView();
//            }
//
//            @Override
//            public void onFailure(Call<Photo[]> call, Throwable t) {
//                Log.e("PhotosActivity", "Failed to fetch photos: " + t.getMessage());
//                showToast("Error, couldn't get information");
//            }
//        });
//    }
//    private void fetchPhotosById(String enteredId) {
//        if (allPhotos != null) {
//            if (!enteredId.isEmpty()) {
//                try {
//                    int id = Integer.parseInt(enteredId);
//                    filteredPhotos = Arrays.stream(allPhotos)
//                            .filter(photo -> photo.getId() == id)
//                            .toArray(Photo[]::new);
//                } catch (NumberFormatException e) {
//                    showToast("Invalid photo ID");
//                    filteredPhotos = allPhotos; // Show all photos if input is invalid
//                }
//            } else {
//                filteredPhotos = allPhotos; // Show all photos if input is empty
//            }
//            updateRecyclerView();
//        }
//    }
//
//    private void fetchPhotosByTitle(String enteredTitle) {
//        if (allPhotos != null) {
//            if (!enteredTitle.isEmpty()) {
//                filteredPhotos = Arrays.stream(allPhotos)
//                        .filter(photo -> photo.getTitle().toLowerCase().contains(enteredTitle.toLowerCase()))
//                        .toArray(Photo[]::new);
//            } else {
//                filteredPhotos = allPhotos; // Show all photos if input is empty
//            }
//            updateRecyclerView();
//        }
//    }
//
//    private void filterPhotosByAlbumId(String enteredAlbumId) {
//        if (allPhotos != null) {
//            if (!enteredAlbumId.isEmpty()) {
//                try {
//                    int albumId = Integer.parseInt(enteredAlbumId);
//                    filteredPhotos = Arrays.stream(allPhotos)
//                            .filter(photo -> photo.getAlbumId() == albumId)
//                            .toArray(Photo[]::new);
//                } catch (NumberFormatException e) {
//                    showToast("Invalid album ID");
//                    filteredPhotos = allPhotos; // Show all photos if input is invalid
//                }
//            } else {
//                filteredPhotos = allPhotos; // Show all photos if input is empty
//            }
//            updateRecyclerView();
//        }
//    }
//
//    private void updateRecyclerView() {
//        PhotosAdapter photosAdapter = new PhotosAdapter(filteredPhotos);
//        recyclerViewPhotos.setAdapter(photosAdapter);
//    }
//
//    private void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//    }
//}


// SECOND VERSION all 3 filters together
package com.example.myfakeapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosActivity extends AppCompatActivity {
    private RecyclerView recyclerViewPhotos;
    private JsonPlaceholderApi jsonPlaceholderApi;
    private EditText editTextPhotoId;
    private EditText editTextPhotoTitle;
    private EditText editTextFilterAlbumId;
    private Button buttonFetchPhotoById;
    private Button buttonFetchPhotoByTitle;
    private Button buttonFilterAlbum;
    private Button buttonBack;
    private Photo[] filteredPhotos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        findViewsById();
        setOnClickListeners();
        jsonPlaceholderApi = ApiClient.getClient().create(JsonPlaceholderApi.class);
        fetchPhotos();
    }

    // Method to initialize views by their IDs
    public void findViewsById() {
        recyclerViewPhotos = findViewById(R.id.recycler_view_photos);
        editTextPhotoId = findViewById(R.id.edit_text_photo_id);
        editTextFilterAlbumId = findViewById(R.id.edit_text_filter_album_id);
        editTextPhotoTitle = findViewById(R.id.edit_text_photo_title);
        buttonFetchPhotoById = findViewById(R.id.button_get_photo_by_id);
        buttonFilterAlbum = findViewById(R.id.button_filter_album);
        buttonFetchPhotoByTitle = findViewById(R.id.button_get_photo_by_title);
        buttonBack = findViewById(R.id.button_back);
    }

    // Method to set click listeners for buttons
    public void setOnClickListeners() {
        buttonFetchPhotoById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Re-fetch photos based on entered ID
                fetchPhotos();
            }
        });

        buttonFetchPhotoByTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Re-fetch photos based on entered title
                fetchPhotos();
            }
        });

        buttonFilterAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Re-fetch photos based on entered album ID
                fetchPhotos();
            }
        });

        // Update RecyclerView when any of the filter fields is cleared
        editTextPhotoId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && editTextPhotoId.getText().toString().isEmpty()) {
                    fetchPhotos();
                }
            }
        });

        editTextPhotoTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && editTextPhotoTitle.getText().toString().isEmpty()) {
                    fetchPhotos();
                }
            }
        });

        editTextFilterAlbumId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && editTextFilterAlbumId.getText().toString().isEmpty()) {
                    fetchPhotos();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the main activity
                Intent intent = new Intent(PhotosActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to fetch photos from the API
    private void fetchPhotos() {
        Call<Photo[]> call = jsonPlaceholderApi.getPhotosArray();
        call.enqueue(new Callback<Photo[]>() {
            @Override
            public void onResponse(Call<Photo[]> call, Response<Photo[]> response) {
                if (!response.isSuccessful()) {
                    // Log error if fetching photos is unsuccessful
                    Log.e("PhotosActivity", "Failed to fetch photos: " + response.code());
                    return;
                }

                // Get the array of photos from the response
                Photo[] photosArray = response.body();

                if (photosArray != null) {
                    // Get entered inputs from EditText fields
                    final String enteredPhotoId = editTextPhotoId.getText().toString().trim();
                    final String enteredPhotoTitle = editTextPhotoTitle.getText().toString().trim();
                    final String enteredAlbumId = editTextFilterAlbumId.getText().toString().trim();

                    // Check if entered inputs exist in the fetched photos
                    boolean photoIdExists = Arrays.stream(photosArray)
                            .anyMatch(photo -> String.valueOf(photo.getId()).equals(enteredPhotoId));
                    boolean photoTitleExists = Arrays.stream(photosArray)
                            .anyMatch(photo -> photo.getTitle().contains(enteredPhotoTitle));
                    boolean albumIdExists = Arrays.stream(photosArray)
                            .anyMatch(photo -> String.valueOf(photo.getAlbumId()).equals(enteredAlbumId));

                    if (!photoIdExists && !enteredPhotoId.isEmpty()) {
                        // Show error message if entered photo ID doesn't exist
                        Toast.makeText(PhotosActivity.this, "Photo ID doesn't exist", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (!albumIdExists && !enteredAlbumId.isEmpty()) {
                        // Show error message if entered album ID doesn't exist
                        Toast.makeText(PhotosActivity.this, "Album ID doesn't exist", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (!photoTitleExists && !enteredPhotoTitle.isEmpty()) {
                        // Show error message if entered photo title doesn't exist
                        Toast.makeText(PhotosActivity.this, "Photo title doesn't exist", Toast.LENGTH_LONG).show();
                        return;
                    }

                    // Check if any of the input fields (photo ID, album ID, or photo title) is not empty
                    if (!enteredPhotoId.isEmpty() || !enteredAlbumId.isEmpty() || !enteredPhotoTitle.isEmpty()) {
                        // Apply filtering if any filter criteria is entered
                        filteredPhotos = Arrays.stream(photosArray)
                                .filter(photo ->
                                        // Check if the entered photo ID matches the photo's ID, if entered
                                        (enteredPhotoId.isEmpty() || String.valueOf(photo.getId()).equals(enteredPhotoId)) &&
                                                // Check if the entered album ID matches the photo's album ID, if entered
                                                (enteredAlbumId.isEmpty() || String.valueOf(photo.getAlbumId()).equals(enteredAlbumId)) &&
                                                // Check if the entered photo title is contained within the photo's title, if entered
                                                (enteredPhotoTitle.isEmpty() || photo.getTitle().contains(enteredPhotoTitle)))
                                // Convert the filtered stream back into an array of photos
                                .toArray(Photo[]::new);
                    } else {
                        // If no filter criteria is entered, display all photos
                        filteredPhotos = photosArray;
                    }


                    // Set up RecyclerView with the filtered photos
                    PhotosAdapter photosAdapter = new PhotosAdapter(filteredPhotos);
                    recyclerViewPhotos.setAdapter(photosAdapter);
                }
            }

            @Override
            public void onFailure(Call<Photo[]> call, Throwable t) {
                // Log error if fetching photos fails
                Log.e("PhotosActivity", "Failed to fetch photos: " + t.getMessage());
                Toast.makeText(PhotosActivity.this, "Error, couldn't get information", Toast.LENGTH_LONG).show();
            }
        });
    }

}

