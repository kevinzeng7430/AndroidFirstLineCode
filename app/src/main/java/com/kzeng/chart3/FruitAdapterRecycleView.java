package com.kzeng.chart3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kzeng.R;

import java.util.List;

public class FruitAdapterRecycleView extends RecyclerView.Adapter<FruitAdapterRecycleView.ViewHolder> {
    private final List<Fruit> mFruitList;
     static class ViewHolder extends RecyclerView.ViewHolder {
         View view;
         ImageView fruitImageView;
         TextView fruitTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            fruitImageView = itemView.findViewById(R.id.fruit_image);
            fruitTextView = itemView.findViewById(R.id.fruit_name);
        }
    }
    public FruitAdapterRecycleView (List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item_recyclerview_second, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.view.setOnClickListener(v->{
            int position = holder.getBindingAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Toast.makeText(v.getContext(), "you clicked view " + fruit.getName(), Toast.LENGTH_SHORT).show();
        });
        holder.fruitImageView.setOnClickListener(v->{
            int position = holder.getBindingAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Toast.makeText(v.getContext(), "you clicked Imageview " + fruit.getName(), Toast.LENGTH_SHORT).show();
        });
        holder.fruitTextView.setOnClickListener(v->{
            int position = holder.getBindingAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Toast.makeText(v.getContext(), "you clicked Textview " + fruit.getName(), Toast.LENGTH_SHORT).show();
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImageView.setImageResource(fruit.getImageId());
        holder.fruitTextView.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
