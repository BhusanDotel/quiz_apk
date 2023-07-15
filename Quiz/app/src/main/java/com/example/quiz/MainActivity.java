package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnScore;
    private Button startQuizBtn;

    private Button btn_about;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        btnScore = findViewById(R.id.btn_score);
        startQuizBtn = findViewById(R.id.startQuizBtn);
        btn_about =findViewById(R.id.btn_about);

        startQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, questionsactivity.class);
                startActivity(intent);
            }
        });

        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, aboutactivity.class);
                startActivity(intent);
            }
        });

    }

    private void showData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                DatabaseContract.UserEntry._ID,
                DatabaseContract.UserEntry.COLUMN_NAME,
                DatabaseContract.UserEntry.COLUMN_SCORE
        };

        Cursor cursor = db.query(
                DatabaseContract.UserEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        StringBuilder data = new StringBuilder();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.UserEntry._ID));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserEntry.COLUMN_NAME));
            int score = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.UserEntry.COLUMN_SCORE));
            data.append("Player: ").append(id).append("    ").append("Name: ").append(username).append("    ").append("Score: ").append(score).append("\n\n");
        }

        cursor.close();

        // Start new activity to display data
        DisplayScoreActivity.startActivity(this, data.toString());
    }


}
