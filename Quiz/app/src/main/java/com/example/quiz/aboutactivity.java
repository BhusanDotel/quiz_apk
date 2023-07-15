package com.example.quiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class aboutactivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }
    public void openInstagram(View view) {
        Uri uri = Uri.parse("https://www.instagram.com/bhusan_dotel/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void openFacebook(View view) {
        Uri uri = Uri.parse("https://www.facebook.com/profile.php?id=100009028581010");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
