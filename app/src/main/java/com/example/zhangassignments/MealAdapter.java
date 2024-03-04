package com.example.zhangassignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private Context context;
    private List<Meal> mealList;

    public MealAdapter(Context context, List<Meal> mealList) {
        this.context = context;
        this.mealList = mealList;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        Meal meal = mealList.get(position);
        holder.mealNameTextView.setText(meal.toString());
        // Use Picasso to load the image. Make sure to include Picasso in your build.gradle
        Picasso.get().load(meal.imgUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder {
        public TextView mealNameTextView;
        public ImageView imageView;

        public MealViewHolder(View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.item_tv_mealinfo);
            imageView = itemView.findViewById(R.id.item_iv_mealimg);
        }
    }
}
