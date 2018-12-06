package com.example.android.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.test1.POJO.Forecastday;
import com.example.android.test1.POJO.Weather;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Forecastday> {

    private Weather weather;
    List<Forecastday> list;
    Context mContext;
    private int lastPosition = -1;

    public CustomAdapter(Weather data, Context context) {
        super(context, R.layout.row_item_weather, data.getForecast().getForecastday());
        this.weather = weather;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Forecastday forecastday = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_weather, parent, false);

            viewHolder.tvDate = convertView.findViewById(R.id.tvDate);
            viewHolder.tvText = convertView.findViewById(R.id.tvText);
            viewHolder.tvMaxtemp = convertView.findViewById(R.id.maxtemp_c);
            viewHolder.tvMintemp = convertView.findViewById(R.id.mintemp_c);
            viewHolder.tvAvgtemp = convertView.findViewById(R.id.avgtemp_c);
            viewHolder.tvMaxwind = convertView.findViewById(R.id.maxwind_kph);
            viewHolder.tvTotalprecip = convertView.findViewById(R.id.totalprecip_mm);
            viewHolder.tvSunrise = convertView.findViewById(R.id.sunrise);
            viewHolder.tvSunset = convertView.findViewById(R.id.sunset);
            viewHolder.tvMoonrise = convertView.findViewById(R.id.moonrise);
            viewHolder.tvmoonset = convertView.findViewById(R.id.moonset);
            viewHolder.ivIcon = convertView.findViewById(R.id.ivIcon);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

       /* Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);*/
        lastPosition = position;

        viewHolder.tvDate.setText(forecastday.getDate());
        viewHolder.tvText.setText(forecastday.getDay().getCondition().getText());
        viewHolder.tvMaxtemp.setText("Max temp " + forecastday.getDay().getMaxtempC() + " °C");
        viewHolder.tvMintemp.setText("Min temp " + forecastday.getDay().getMintempC() + " °C");
        viewHolder.tvAvgtemp.setText("Avr temp " + forecastday.getDay().getAvgtempC() + " °C");
        viewHolder.tvMaxwind.setText("Air speed " + forecastday.getDay().getMaxwindKph() + " kph");
        viewHolder.tvTotalprecip.setText("Mill of mercury " + forecastday.getDay().getTotalprecipMm() + " mm");
        viewHolder.tvSunrise.setText(forecastday.getAstro().getSunrise());
        viewHolder.tvSunset.setText(forecastday.getAstro().getSunset());
        viewHolder.tvMoonrise.setText(forecastday.getAstro().getMoonrise());
        viewHolder.tvmoonset.setText(forecastday.getAstro().getMoonset());

        Picasso.with(mContext)
                .load(("https:" + forecastday.getDay().getCondition().getIcon()))
                .into(ViewHolder.ivIcon);

        return convertView;
    }

    private static class ViewHolder {
        TextView tvDate;
        TextView tvText;
        TextView tvMaxtemp;
        TextView tvMintemp;
        TextView tvAvgtemp;
        TextView tvMaxwind;
        TextView tvTotalprecip;
        TextView tvSunrise;
        TextView tvSunset;
        TextView tvMoonrise;
        TextView tvmoonset;
        static ImageView ivIcon;
    }
}
