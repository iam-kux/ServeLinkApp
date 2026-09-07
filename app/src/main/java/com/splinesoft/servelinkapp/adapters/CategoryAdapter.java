package com.splinesoft.servelinkapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final List<Category> categories;
    private final OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    public CategoryAdapter(List<Category> categories, OnCategoryClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(categories.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgIcon;
        private final TextView tvName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.ivCategoryIcon);
            tvName = itemView.findViewById(R.id.tvCategoryName);
        }

        public void bind(Category category, OnCategoryClickListener listener) {
            tvName.setText(category.getName());

            // Map string icon name dynamically to drawable resource
            int resId = itemView.getContext().getResources().getIdentifier(
                    category.getIcon(),
                    "drawable",
                    itemView.getContext().getPackageName()
            );

            if (resId != 0) {
                imgIcon.setImageResource(resId);
            } else {
                imgIcon.setImageResource(R.drawable.logo); // fallback icon
            }

            itemView.setOnClickListener(v -> listener.onCategoryClick(category));
        }
    }
}
