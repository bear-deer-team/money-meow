package com.example.money_meow.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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


public class HistoryListForHome extends RecyclerView.Adapter<HistoryListForHome.TransactionViewHolder> {

    List<Transaction> transactionList;
    Context context;

    public HistoryListForHome(List<Transaction> transactionList, Context context) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction,parent,false);

        return new TransactionViewHolder(view);
    }
    // set các giá trị của historylist
    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
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
        if(transactionList!=null){
            return transactionList.size();
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
