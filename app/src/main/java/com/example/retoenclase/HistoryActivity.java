package com.example.retoenclase;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import com.example.retoenclase.adapter.QuoteAdapter;
import com.example.retoenclase.db.AppDatabase;
import com.example.retoenclase.model.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView rvHistory;
    private QuoteAdapter adapter;
    private AppDatabase db;
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistory = findViewById(R.id.rvHistory);
        Button btnBack = findViewById(R.id.btnBack);

        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuoteAdapter(new ArrayList<>());
        rvHistory.setAdapter(adapter);

        db = AppDatabase.getInstance(this);
        executor = Executors.newSingleThreadExecutor();

        loadHistory();

        btnBack.setOnClickListener(v -> finish());
    }

    private void loadHistory() {
        executor.execute(() -> {
            List<Quote> quotes = db.quoteDao().getAllQuotes();
            runOnUiThread(() -> {
                adapter.setQuotes(quotes);
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
