package com.example.bottomnav.notify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bottomnav.R;

import java.util.ArrayList;

class CustomAdapter4 extends BaseAdapter {
    private LayoutInflater inflater;
    private int resourcedId;
    private String[] items;
    private Double[] items2;
    private String[] items3;
    private String[] items4;

    static class ViewHolder {
        TextView textView0;
        TextView textView2;
        TextView textView3;
        TextView textView4;
    }

    CustomAdapter4(Context context, int resourcedId, ArrayList<String> items, ArrayList<Double> items2, ArrayList<String> items3, ArrayList<String> items4) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourcedId = resourcedId;
        this.items = items.toArray(new String[0]);
        this.items2 = items2.toArray(new Double[0]);
        this.items3 = items3.toArray(new String[0]);
        this.items4 = items4.toArray(new String[0]);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(resourcedId, parent, false);

            holder = new ViewHolder();
            holder.textView0 = convertView.findViewById(R.id.textView9);
            holder.textView2 = convertView.findViewById(R.id.textView11);
            holder.textView3 = convertView.findViewById(R.id.gram);
            holder.textView4 = convertView.findViewById(R.id.detail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView0.setText(items[position]);
        holder.textView2.setText(items2[position] + "kcal");
        holder.textView3.setText(items3[position]);
        holder.textView4.setText(items4[position]);

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
