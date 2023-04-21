package com.example.money_meow.home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_meow.R;
import com.example.money_meow.transaction.Transaction;

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
        }
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


        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImg = itemView.findViewById(R.id.categoryImg);
            name = itemView.findViewById(R.id.name);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.date);

        }
    }
}
