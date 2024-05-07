package com.example.myfakeapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class UserDetailsActivity extends AppCompatActivity {

    // Views
    private TextView nameTextView;
    private TextView usernameTextView;
    private TextView userIdTextView;
    private TextView userEmailTextView;
    private TextView userCompanyNameTextView;
    private TextView userCompanyCatchPhraseTextView;
    private TextView userCompanyBsTextView;
    private TextView userStreetNameTextView;
    private TextView userCityNameTextView;
    private TextView userZipCodeTextView;
    private Button buttonBackUserList;
    private Button buttonBackMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        findViewsById();
        setOnClickListeners();
        displayUserData();
    }

    // Initialize views from XML layout
    private void findViewsById() {
        nameTextView = findViewById(R.id.text_view_name);
        usernameTextView = findViewById(R.id.text_view_user_name);
        userIdTextView = findViewById(R.id.text_view_user_id);
        userEmailTextView = findViewById(R.id.text_view_user_email);
        userCompanyNameTextView = findViewById(R.id.text_view_user_company_name);
        userCompanyCatchPhraseTextView = findViewById(R.id.text_view_user_company_catch_phrase);
        userCompanyBsTextView = findViewById(R.id.text_view_user_company_catch_bs);
        userStreetNameTextView = findViewById(R.id.text_view_user_street_name);
        userCityNameTextView = findViewById(R.id.text_view_user_city_name);
        userZipCodeTextView = findViewById(R.id.text_view_user_zip_code);
        buttonBackUserList = findViewById(R.id.button_back_to_user_list);
        buttonBackMainMenu = findViewById(R.id.button_back_to_main_menu);
    }

    public void setOnClickListeners() {
        // Set onClickListeners for buttons
        buttonBackMainMenu.setOnClickListener(v -> {
            Intent intent = new Intent(UserDetailsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        buttonBackUserList.setOnClickListener(v -> {
            Intent intent = new Intent(UserDetailsActivity.this, UsersActivity.class);
            startActivity(intent);
        });
    }

    // Receive user data from Intent and display it in TextViews
    private void displayUserData() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("userJson")) {
            String userJson = intent.getStringExtra("userJson");
            Gson gson = new Gson();
            User user = gson.fromJson(userJson, User.class);
            if (user != null) {
                nameTextView.setText("Name: " + user.getName());
                usernameTextView.setText("Username: " + user.getUsername());
                userIdTextView.setText("User ID: " + user.getId());
                userEmailTextView.setText("Email: " + user.getEmail());
                userCompanyNameTextView.setText("Company: " + user.getCompany().getName());
                userCompanyCatchPhraseTextView.setText("Catch Phrase: " + user.getCompany().getCatchPhrase());
                userCompanyBsTextView.setText("BS: " + user.getCompany().getBs());
                userStreetNameTextView.setText("Street: " + user.getAddress().getStreet());
                userCityNameTextView.setText("City: " + user.getAddress().getCity());
                userZipCodeTextView.setText("ZIP Code: " + user.getAddress().getZipcode());
            }
        }
    }
}
