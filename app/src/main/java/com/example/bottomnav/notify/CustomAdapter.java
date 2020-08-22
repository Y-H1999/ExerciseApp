package com.example.bottomnav.notify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bottomnav.R;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int resourcedId;
    private String[] items;
    private String[] items2;
    private String[] items3;
    private String[] items4;

    static class ViewHolder {
        Button editButton;
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        Button deleteButton;
    }

    CustomAdapter(Context context, int resourcedId, ArrayList<String> items, ArrayList<String> items2, ArrayList<String> items3, ArrayList<String> items4) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourcedId = resourcedId;
        this.items = items.toArray(new String[0]);
        this.items2 = items2.toArray(new String[0]);
        this.items3 = items3.toArray(new String[0]);
        this.items4 = items4.toArray(new String[0]);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(resourcedId, parent, false);

            holder = new ViewHolder();
            holder.editButton = convertView.findViewById(R.id.button2);
            holder.textView = convertView.findViewById(R.id.textView);
            holder.textView2 = convertView.findViewById(R.id.textView2);
            holder.textView3 = convertView.findViewById(R.id.detail);
            holder.textView4 = convertView.findViewById(R.id.gram);
            holder.deleteButton = convertView.findViewById(R.id.button3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(items[position]);
        holder.textView2.setText(items2[position]);
        holder.textView3.setText(items3[position]);
        holder.textView4.setText(items4[position]);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView) parent).performItemClick(view, position, R.id.button2);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView) parent).performItemClick(view, position, R.id.button3);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}