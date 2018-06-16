package ibm.code.challenge.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import ibm.code.challenge.R;
import ibm.code.challenge.models.ConsolidatedWeather;
import ibm.code.challenge.utils.Constants;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.WeatherForecastItemVH> {

    List<ConsolidatedWeather> consolidatedWeatherList;

    public WeatherForecastAdapter(List<ConsolidatedWeather> consolidatedWeatherList) {
        this.consolidatedWeatherList = consolidatedWeatherList;
    }

    @Override
    public WeatherForecastItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_forecast, parent, false);
        return new WeatherForecastItemVH(view);
    }

    @Override
    public void onBindViewHolder(WeatherForecastItemVH holder, int position) {

        if (holder instanceof WeatherForecastItemVH && consolidatedWeatherList != null) {
            holder.bindData(consolidatedWeatherList.get(position));

        }

    }

    @Override
    public int getItemCount() {
        return consolidatedWeatherList != null ? consolidatedWeatherList.size() : 0;
    }

    public class WeatherForecastItemVH extends RecyclerView.ViewHolder {

        TextView tv_status;
        TextView tv_date;
        TextView tv_temp;
        TextView tv_min_temp;
        TextView tv_max_temp;
        TextView tv_humidity;


        public WeatherForecastItemVH(View itemView) {
            super(itemView);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_temp = itemView.findViewById(R.id.tv_temp);
            tv_min_temp = itemView.findViewById(R.id.tv_min_temp);
            tv_max_temp = itemView.findViewById(R.id.tv_max_temp);
            tv_humidity = itemView.findViewById(R.id.tv_humidity);

        }

        public void bindData(ConsolidatedWeather consolidatedWeather) {
            if (consolidatedWeather != null) {
                setText(tv_status, Constants.STATUS + consolidatedWeather.getWeatherStateName());
                setText(tv_date, Constants.DATE + consolidatedWeather.getApplicableDate());
                setText(tv_temp, Constants.TEMPERATURE + String.valueOf(consolidatedWeather.getTheTemp()));
                setText(tv_min_temp, Constants.MINIMUM_TEMPERATURE + consolidatedWeather.getMinTemp());
                setText(tv_max_temp, Constants.MAXIMUM_TEMPERATURE + consolidatedWeather.getMaxTemp());
                setText(tv_humidity, Constants.HUMIDITY + consolidatedWeather.getHumidity());

            }


        }

        public void setText(TextView textView, String text) {
            if (textView == null || StringUtils.isEmpty(text))
                textView.setVisibility(View.GONE);
            else {
                textView.setText(text);
                textView.setVisibility(View.VISIBLE);
            }
        }


    }

}
