package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class shownameform extends AppCompatActivity {

    private EditText editTextname;
    private Button submit_name;

    private DatabaseHelper dbHelper;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_form);

        editTextname = findViewById(R.id.name);
        submit_name = findViewById(R.id.submit_name);

        dbHelper = new DatabaseHelper(this);

        score = getIntent().getIntExtra("score", 0);

        submit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

    }

    private void insertData() {
        String name = editTextname.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, "Don't you know your name?", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.UserEntry.COLUMN_NAME, name);
            values.put(DatabaseContract.UserEntry.COLUMN_SCORE, score);
            long newRowId = db.insert(DatabaseContract.UserEntry.TABLE_NAME, null, values);

            if (newRowId != -1) {
                Toast.makeText(this, "Score saved successfully!", Toast.LENGTH_SHORT).show();
                editTextname.setText("");
            } else {
                Toast.makeText(this, "Error in saving score!", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(shownameform.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
