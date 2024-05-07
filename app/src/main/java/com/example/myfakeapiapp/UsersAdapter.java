//package com.example.myfakeapiapp;
//
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
//    User[] users;
//
//    public UsersAdapter(User[] users) {
//        this.users = users;
//    }
//
//    @Override
//    public int getItemCount() {
//        return users.length;
//    }
//
//    @NonNull
//    @Override
//    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
//        return new UsersAdapter.UsersViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
//        holder.bind(users[position]);
//
//    }
//
//
//    static class UsersViewHolder extends RecyclerView.ViewHolder {
//        private TextView userID;
//        private TextView Name;
//        private TextView userName;
//        private TextView userEmail;
//
//        public UsersViewHolder(View itemView) {
//            super(itemView);
//            userID = itemView.findViewById(R.id.text_view_user_id);
//            Name = itemView.findViewById(R.id.text_view_name);
//            userName = itemView.findViewById(R.id.text_view_user_name);
//            userEmail = itemView.findViewById(R.id.text_view_user_email);
//
//        }
//
//        public void bind(User user) {
//            Name.setText("Full Name: " + user.getName());
//            userName.setText("UserName: " + user.getUsername());
//            userID.setText("User ID: " + String.valueOf(user.getId()));
//            userEmail.setText("User Email: " + user.getEmail());
//        }
//
//    }
//
//}




package com.example.myfakeapiapp;

import com.google.gson.Gson;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private User[] users;
    private Context context; // Add context variable

    public UsersAdapter(User[] users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return users.length;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.bind(users[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    User user = users[clickedPosition];
                    Gson gson = new Gson();
                    String userJson = gson.toJson(user);

                    Intent intent = new Intent(context, UserDetailsActivity.class);
                    intent.putExtra("userJson", userJson);
                    context.startActivity(intent);
                }
            }
        });
    }


    static class UsersViewHolder extends RecyclerView.ViewHolder {
        private TextView userID;
        private TextView Name;
        private TextView userName;
        private TextView userEmail;

        public UsersViewHolder(View itemView) {
            super(itemView);
            userID = itemView.findViewById(R.id.text_view_user_id);
            Name = itemView.findViewById(R.id.text_view_name);
            userName = itemView.findViewById(R.id.text_view_user_name);
            userEmail = itemView.findViewById(R.id.text_view_user_email);
        }

        public void bind(User user) {
            Name.setText("Full Name: " + user.getName());
            userName.setText("UserName: " + user.getUsername());
            userID.setText("User ID: " + String.valueOf(user.getId()));
            userEmail.setText("User Email: " + user.getEmail());
        }
    }
}
