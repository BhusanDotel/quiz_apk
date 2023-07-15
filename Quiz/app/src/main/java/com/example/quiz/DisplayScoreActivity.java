package com.example.quiz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.DatabaseContract;
import com.example.quiz.DatabaseHelper;
import com.example.quiz.R;

public class DisplayScoreActivity extends AppCompatActivity {

    private static final String EXTRA_DATA = "extra_data";

    private TextView textViewData;
    private Button btnClear;

    public static void startActivity(Context context, String data) {
        Intent intent = new Intent(context, DisplayScoreActivity.class);
        intent.putExtra(EXTRA_DATA, data);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_score);

        textViewData = findViewById(R.id.textViewData);
        btnClear = findViewById(R.id.btnClear);

        String data = getIntent().getStringExtra(EXTRA_DATA);
        textViewData.setText(data);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Clear")
                .setMessage("Are you sure you want to clear all data?")
                .setPositiveButton("Clear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearData();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void clearData() {
        // Clear data from the database table
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseContract.UserEntry.TABLE_NAME, null, null);

        // Update the text view to reflect the cleared data
        textViewData.setText("Data cleared");

        // Optionally, you can display a toast message
        Toast.makeText(this, "Data cleared", Toast.LENGTH_SHORT).show();
    }
}
