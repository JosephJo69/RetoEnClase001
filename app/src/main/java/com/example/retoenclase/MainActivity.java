package com.example.retoenclase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnGenerateFrase = findViewById(R.id.btnGenerateFrase);
        Button btnHistory = findViewById(R.id.btnHistory);
        Button btnExit = findViewById(R.id.btnExit);

        btnGenerateFrase.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuoteActivity.class);
            startActivity(intent);
        });

        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        });

        btnExit.setOnClickListener(v -> finish());
    }
}