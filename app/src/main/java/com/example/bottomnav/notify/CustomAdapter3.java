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

class CustomAdapter3 extends BaseAdapter {
    private LayoutInflater inflater;
    private int resourcedId;
    private String[] items0;
    private String[] items;
    private String[] items1;
    private String[] items2;


    static class ViewHolder {
        TextView textView0;
        TextView textView;
        TextView textView1;
        TextView textView2;
        Button editButton;
    }

    CustomAdapter3(Context context, int resourcedId, ArrayList<String> items0, ArrayList<String> items,ArrayList<String> items1 , ArrayList<String> items2) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourcedId = resourcedId;
        this.items0 = items0.toArray(new String[0]);
        this.items = items.toArray(new String[0]);
        this.items1 = items1.toArray(new String[0]);
        this.items2 = items2.toArray(new String[0]);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(resourcedId, parent, false);

            holder = new ViewHolder();
            holder.editButton = convertView.findViewById(R.id.button2);
            holder.textView0 = convertView.findViewById(R.id.searchedtext0);
            holder.textView = convertView.findViewById(R.id.searchedtext);
            holder.textView1 = convertView.findViewById(R.id.searchedtext1);
            holder.textView2 = convertView.findViewById(R.id.detail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView0.setText(items0[position]);
        holder.textView.setText(items[position]);
        holder.textView1.setText(items1[position]);
        holder.textView2.setText(items2[position]);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView) parent).performItemClick(view, position, R.id.button2);
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
