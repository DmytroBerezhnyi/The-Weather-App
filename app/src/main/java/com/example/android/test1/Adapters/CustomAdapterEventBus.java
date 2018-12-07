package com.example.android.test1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.test1.POJO.WeatherDB;
import com.example.android.test1.R;

import java.util.List;

public class CustomAdapterEventBus extends ArrayAdapter<WeatherDB> {

    private Context context;
    private int lastPosition = -1;

    public CustomAdapterEventBus(List<WeatherDB> listWeatherDB, Context context) {
        super(context, R.layout.row_item_weather_db, listWeatherDB);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        WeatherDB weatherDB = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_weather_db, parent, false);

            viewHolder.tvTime = convertView.findViewById(R.id.tvTime);
            viewHolder.tvId = convertView.findViewById(R.id.tvId);
            viewHolder.tvJson = convertView.findViewById(R.id.tvJson);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.tvTime.setText(weatherDB.getTime() + "");
        viewHolder.tvId.setText(weatherDB.getId() + "");
        viewHolder.tvJson.setText(weatherDB.getJsonObject());

        return convertView;
    }

    private static class ViewHolder {
        TextView tvTime;
        TextView tvId;
        TextView tvJson;
    }
}
