package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class customAdapter extends ArrayAdapter<message> {
    private final Activity context;
    private final ArrayList<message>list;

    public customAdapter(Activity context, ArrayList<message> list) {
        super(context, R.layout.dummy);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.dummy, null, true);
        TextView tw1 = (TextView) rowView.findViewById(R.id.email);
        TextView tw2 = (TextView) rowView.findViewById(R.id.message);
        TextView tw3 = (TextView) rowView.findViewById(R.id.time);

        String time=list.get(position).getTime();
        String mes=list.get(position).getMessage();
        String em=list.get(position).getEmail();
        tw1.setText(em);
        tw2.setText(mes);
        tw3.setText(time);

        return rowView;
    }
}
