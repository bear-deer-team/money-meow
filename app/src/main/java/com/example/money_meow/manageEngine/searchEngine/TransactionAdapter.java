package com.example.money_meow.manageEngine.searchEngine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_meow.R;
import com.example.money_meow.transaction.Transaction;
import com.example.money_meow.transaction.TransactionAction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    List<Transaction> transactionListSearch;
    Context context;
    public TransactionAdapter(List<Transaction> transactionListSearch, Context context) {
        this.context = context;
        this.transactionListSearch = transactionListSearch;
    }

    public TransactionAdapter() {

    }

    public void updateList(List<Transaction> transactionListSearch) {
        this.transactionListSearch = transactionListSearch;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionAdapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction,parent,false);

        return new TransactionAdapter.TransactionViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.TransactionViewHolder holder, int position) {
        Transaction transaction = transactionListSearch.get(position);
        if (transaction == null) {
            return;
        } else {
            holder.categoryImg.setImageResource(transaction.getTransactionCategory().getImage(this.context));
            holder.name.setText(transaction.getTransactionCategory().getCategoryName());
            holder.date.setText(transaction.formatDate());
            String amountColor = new String();
            if(transaction.getTransactionType().equals("income")){
                amountColor += "+$" + Double.toString(transaction.getTransactionAmount());
                holder.amount.setText(amountColor);
                holder.amount.setTextColor(Color.rgb(0,255,0));
            }
            else {
                amountColor += "-$" + Double.toString(transaction.getTransactionAmount());
                holder.amount.setText(amountColor);
                holder.amount.setTextColor(Color.rgb(255,0,0));
            }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickGoToTransactionAction(transaction);

                }
            });
        }
    }

    private void onClickGoToTransactionAction(Transaction transaction) {
        TransactionAction.trans = transaction;
        Intent intent = new Intent(context, TransactionAction.class);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(transactionListSearch!=null){
            return transactionListSearch.size();
        }
        return 0;
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder{
        private ImageView categoryImg;
        private TextView name;
        private TextView date;
        private TextView amount;

        private CardView cardView;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.transactionItem);
            categoryImg = itemView.findViewById(R.id.categoryImg);
            name = itemView.findViewById(R.id.name);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.date);

        }
    }
}
