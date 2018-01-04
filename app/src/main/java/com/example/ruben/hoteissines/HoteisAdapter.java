package com.example.ruben.hoteissines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ruben.hoteissines.model.Hotel;

import java.util.List;

/**
 * Created by Ruben on 03/01/2018.
 */

public class HoteisAdapter extends BaseAdapter {
    private List<Hotel> hotelList;
    private Context context;

    public HoteisAdapter(Context context, List<Hotel> hotelList) {
        this.context = context;
        this.hotelList = hotelList;
    }

    @Override
    public int getCount() {
        return this.hotelList.size();
    }

    @Override
    public Hotel getItem(int i) {
        return this.hotelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
        view = LayoutInflater.from(this.context).inflate(R.layout.list_row_hotel, viewGroup, false);
        }

        TextView textView = view.findViewById(R.id.textViewName);
        Hotel hotel = getItem(i);
        textView.setText(hotel.getName());

        return view;
    }

    public void updateList(List<Hotel> hotels) {
        this.hotelList = hotels;
        notifyDataSetChanged();
    }
}
