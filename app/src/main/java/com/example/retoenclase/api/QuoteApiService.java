package com.example.retoenclase.api;

import com.example.retoenclase.model.Quote;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteApiService {
    @GET("https://zenquotes.io/api/random")
    Call<List<Quote>> getRandomQuote();
}
