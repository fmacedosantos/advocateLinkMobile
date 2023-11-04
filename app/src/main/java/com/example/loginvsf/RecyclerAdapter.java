package com.example.loginvsf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Feed> feedArrayList;
    public RecyclerAdapter(Context context, ArrayList<Feed> feedArrayList) {
        this.context = context;
        this.feedArrayList = feedArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feed feed = feedArrayList.get(position);
        holder.title.setText(feed.title);
        holder.titleImage.setImageResource(feed.titleImage);
        holder.mensagem.setText(feed.mensagem);
        holder.imagePost.setImageResource(feed.imagePost);
    }

    @Override
    public int getItemCount() {
        return feedArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView titleImage;
        TextView mensagem;
        ImageView imagePost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            titleImage = itemView.findViewById(R.id.title_image);
            mensagem = itemView.findViewById(R.id.textMMensage); // Certifique-se de que o ID está correto
            imagePost = itemView.findViewById(R.id.imagePost);   // Certifique-se de que o ID está correto
        }
    }
}
