package com.example.money_meow.manageEngine.statistic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_meow.R;

import java.util.List;

public class ByTimeAdapter extends RecyclerView.Adapter<ByTimeAdapter.TimeViewHolder> {
    List<Time> timeListSearch;
    Context context;
    public ByTimeAdapter(List<Time> timeListSearch, Context context) {
        this.context = context;
        this.timeListSearch = timeListSearch;
    }

    public void updateList(List<Time> timeListSearch) {
        this.timeListSearch = timeListSearch;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ByTimeAdapter.TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time,parent,false);

        return new ByTimeAdapter.TimeViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ByTimeAdapter.TimeViewHolder holder, int position) {
        Time time = timeListSearch.get(position);
        if (time == null) {
            return;
        } else {
            holder.date.setText(time.getDate());
            String income = "+$" + Double.toString(time.getIncome());
            holder.income.setText(income);
            String expense = "-$" + Double.toString(time.getExpense());
            holder.expense.setText(expense);

            String total = new String();
            if (time.getTotal() >= 0) {
                total = "+$" + Double.toString(time.getTotal());
            } else {
                total = "-$" + Double.toString(Math.abs(time.getTotal()));
            }
            holder.total.setText(total);

        }
    }

    @Override
    public int getItemCount() {
        if(timeListSearch!=null){
            return timeListSearch.size();
        }
        return 0;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder{
        private TextView date;
        private TextView income;
        private TextView expense;
        private TextView total;

        public TimeViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            income = itemView.findViewById(R.id.income);
            expense = itemView.findViewById(R.id.expense);
            total = itemView.findViewById(R.id.total);
        }
    }
}
