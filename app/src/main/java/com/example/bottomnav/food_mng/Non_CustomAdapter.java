package com.example.bottomnav.food_mng;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bottomnav.R;

import java.util.ArrayList;

public class Non_CustomAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int resourcedId;
    private String[] items;
    private String[] items2;

    static class ViewHolder {
        TextView textView0;
        TextView textView2;
    }

    public Non_CustomAdapter(Context context, int resourcedId, ArrayList<String> items, ArrayList<String> items2) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourcedId = resourcedId;
        this.items = items.toArray(new String[0]);
        this.items2 = items2.toArray(new String[0]);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(resourcedId, parent, false);

            holder = new ViewHolder();
            holder.textView0 = convertView.findViewById(R.id.textView0);
            holder.textView2 = convertView.findViewById(R.id.textView2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String item_cal = items2[position] + "kcal";
        holder.textView0.setText(items[position]);
        holder.textView2.setText(item_cal);
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
