package ibm.code.challenge.views.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import ibm.code.challenge.R;
import ibm.code.challenge.models.WeatherInfo;
import ibm.code.challenge.utils.Constants;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherListItemVH> {

    List<WeatherInfo> weatherInfoList;
    OnListItemClickListener onListItemClickListener;

    public WeatherListAdapter(OnListItemClickListener onListItemClickListener, List<WeatherInfo> weatherInfoList) {
        this.onListItemClickListener = onListItemClickListener;
        this.weatherInfoList = weatherInfoList;
    }

    @NonNull
    @Override
    public WeatherListItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_info, parent, false);
        return new WeatherListItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherListItemVH holder, int position) {
        if (holder instanceof WeatherListItemVH) {
            holder.bindData(weatherInfoList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return weatherInfoList != null ? weatherInfoList.size() : 0;
    }

    public class WeatherListItemVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        ConstraintLayout parent;
        TextView tv_id;
        TextView tv_title;
        TextView tv_type;

        public WeatherListItemVH(View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_type = itemView.findViewById(R.id.tv_type);
            parent.setOnClickListener(this);
        }

        public void bindData(WeatherInfo weatherInfo) {
            if (weatherInfo != null) {
                parent.setTag(weatherInfo.getId());
                setText(tv_id, Constants.ID + weatherInfo.getId());
                setText(tv_title, Constants.TITLE + weatherInfo.getTitle());
                setText(tv_type, Constants.TYPE + weatherInfo.getLocationType());

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

        @Override
        public void onClick(View view) {

            if (view.getTag() instanceof Integer && onListItemClickListener != null)
                onListItemClickListener.onListItemClicked((Integer) view.getTag());

        }
    }

    public interface OnListItemClickListener {
        void onListItemClicked(Integer id);
    }

}
