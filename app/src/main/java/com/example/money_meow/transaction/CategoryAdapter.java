package com.example.money_meow.transaction;

import android.content.Context;
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
import com.example.money_meow.category.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private List<Category> list;
    private Context context;

    public CategoryAdapter(List<Category> list,Context context){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
            Category category = list.get(position);
            if(category == null){
                return;
            }else {
                holder.imgCate.setImageResource(category.getImage(this.context));
                holder.nameCate.setText(category.getCategoryName());
                if(category.getCategoryType().equals("income")){
                    holder.item.setCardBackgroundColor(Color.rgb(142,246,146));
                }
                holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransactionAction.category = category;
                        TransactionAction.cateName.setText(TransactionAction.category.getCategoryName());
                        TransactionAction.cateImg.setImageResource(TransactionAction.category.getImage(context));
                        TransactionAction.categoryLayout.setVisibility(View.GONE);
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgCate;
        private TextView nameCate;

        private CardView item;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCate = itemView.findViewById(R.id.categoryItemImage);
            nameCate = itemView.findViewById(R.id.categoryItemName);
            item = itemView.findViewById(R.id.categotyItem);
        }
    }
}
