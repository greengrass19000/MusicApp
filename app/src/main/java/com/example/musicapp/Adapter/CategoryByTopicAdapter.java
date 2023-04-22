package com.example.musicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Model.Category;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CategoryByTopicAdapter extends RecyclerView.Adapter<CategoryByTopicAdapter.ViewHolder> {
    Context context;
    ArrayList<Category> categoryList;

    public CategoryByTopicAdapter(Context context, ArrayList<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_category_by_topic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.txtCategoryName.setText(category.getName());
        Picasso.get().load(category.getImage()).into(holder.imgBackground);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBackground;
        TextView txtCategoryName;
        public ViewHolder(View itemView) {
            super(itemView);
            imgBackground = itemView.findViewById(R.id.imageviewcategorybytopic);
            txtCategoryName = itemView.findViewById(R.id.textviewcategorybytopic);
        }
    }
}