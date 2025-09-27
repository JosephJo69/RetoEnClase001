package com.example.retoenclase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retoenclase.api.QuoteApiService;
import com.example.retoenclase.db.AppDatabase;
import com.example.retoenclase.model.Quote;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuoteActivity extends AppCompatActivity {
    private TextView tvQuote;
    private TextView tvAuthor;
    private ProgressBar progressBar;
    private QuoteApiService apiService;
    private AppDatabase db;
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        tvQuote = findViewById(R.id.tvQuote);
        tvAuthor = findViewById(R.id.tvAuthor);
        progressBar = findViewById(R.id.progressBar);
        Button btnBack = findViewById(R.id.btnBack);

        db = AppDatabase.getInstance(this);
        executor = Executors.newSingleThreadExecutor();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://zenquotes.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        apiService = retrofit.create(QuoteApiService.class);
        loadRandomQuote();

        btnBack.setOnClickListener(v -> finish());
    }

    private void loadRandomQuote() {
        progressBar.setVisibility(View.VISIBLE);
        apiService.getRandomQuote().enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    Quote quote = response.body().get(0);
                    tvQuote.setText(quote.getQ());
                    tvAuthor.setText("- " + quote.getA());

                    executor.execute(() -> {
                        db.quoteDao().insert(quote);
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(QuoteActivity.this,
                    "Error al cargar la frase: " + t.getMessage(),
                    Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
