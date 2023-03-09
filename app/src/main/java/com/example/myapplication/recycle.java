package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycle extends RecyclerView.Adapter<recycle.ViewHolder> {
private Context context;
private ArrayList<message> list;

    public recycle(Context context, ArrayList<message> list) {
        this.context = context;
        this.list = list;
    }

    public void add(message msg){
        list.add(0,msg);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public recycle.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.message_design,null,true );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recycle.ViewHolder holder, int position) {
holder.username.setText(list.get(position).getEmail());
holder.message.setText(list.get(position).getMessage());
holder.time.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView username,message,time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.email);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);

        }
    }
}
