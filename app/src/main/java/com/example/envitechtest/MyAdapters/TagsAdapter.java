package com.example.envitechtest.MyAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.envitechtest.R;
import com.example.envitechtest.Objects.Tags;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class TagsAdapter extends  RecyclerView.Adapter<TagsAdapter.MyViewHolder>{

    Context context;
    ArrayList<Tags> tagsArrayList;

    public TagsAdapter() {
    }

    public TagsAdapter(Context context, ArrayList<Tags> tagsArrayList) {
        this.context = context;
        this.tagsArrayList = tagsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.tags_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  TagsAdapter.MyViewHolder holder, int position) {
        Tags tags = tagsArrayList.get(position);
        holder.tags_list_IMG_Color.setBackgroundColor(Color.parseColor(tags.Color));
        holder.tags_list_TXT_Label.setText(tags.getLabel());
    }

    @Override
    public int getItemCount() {
        return tagsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ShapeableImageView tags_list_IMG_Color;
        TextView tags_list_TXT_Label;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tags_list_IMG_Color = itemView.findViewById(R.id.tags_list_IMG_Color);
            tags_list_TXT_Label = itemView.findViewById(R.id.tags_list_TXT_Label);
        }
    }
}
