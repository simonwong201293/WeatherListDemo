package com.sw.demo.weatherlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sw.demo.weatherlist.model.Weather;

/**
 * Created by SimonWong on 15/11/2016.
 */

public class WeatherAdapter extends BaseAdapter {

    private Weather weather;
    private LayoutInflater mInflater;

    public WeatherAdapter(android.content.Context context, Weather weather) {
        this.weather = weather;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (weather.list == null || weather.list.size() <= 0) ? 0 : weather.list.size();
    }

    @Override
    public Object getItem(int i) {
        return (weather.list == null || weather.list.size() <= 0 || weather.list.size() <= i) ? null : weather.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.weather_item, viewGroup, false);
            holder = new ViewHolder();
            holder.timeTv = (android.widget.TextView) view.findViewById(R.id.time_tv);
            holder.tempTv = (android.widget.TextView) view.findViewById(R.id.temp_tv);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        holder.timeTv.setText(((Weather.List)getItem(i)).dtTxt);
        holder.tempTv.setText(String.valueOf(((Weather.List)getItem(i)).main.temp) + "°C");
        return view;
    }

    class ViewHolder {
        android.widget.TextView timeTv, tempTv;
    }
}
