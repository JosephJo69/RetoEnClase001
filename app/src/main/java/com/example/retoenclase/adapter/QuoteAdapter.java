package com.example.retoenclase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retoenclase.R;
import com.example.retoenclase.model.Quote;
import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {
    private List<Quote> quotes;

    public QuoteAdapter(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quote, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.tvQuote.setText(quote.getQ());
        holder.tvAuthor.setText("- " + quote.getA());
        holder.tvTimestamp.setText(quote.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return quotes != null ? quotes.size() : 0;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
        notifyDataSetChanged();
    }

    static class QuoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuote;
        TextView tvAuthor;
        TextView tvTimestamp;

        QuoteViewHolder(View itemView) {
            super(itemView);
            tvQuote = itemView.findViewById(R.id.tvQuoteItem);
            tvAuthor = itemView.findViewById(R.id.tvAuthorItem);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }
    }
}
