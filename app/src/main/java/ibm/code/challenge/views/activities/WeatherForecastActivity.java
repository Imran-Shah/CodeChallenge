package ibm.code.challenge.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import ibm.code.challenge.R;
import ibm.code.challenge.models.WeatherForecast;
import ibm.code.challenge.presenters.WeatherForecastPresenter;
import ibm.code.challenge.utils.CommonUtils;
import ibm.code.challenge.utils.Constants;
import ibm.code.challenge.views.adapters.WeatherForecastAdapter;

public class WeatherForecastActivity extends AppCompatActivity implements WeatherForecastPresenter.OnForecastResponseListener {

    private RecyclerView rv_forecast;
    private ProgressBar progressBar;
    private WeatherForecastAdapter adapter;
    private WeatherForecastPresenter presenter;


    //setting up components and performing necessary requestss
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        rv_forecast = findViewById(R.id.rv_weather_forecast);
        progressBar = findViewById(R.id.progressBar);
        presenter = new WeatherForecastPresenter(this);


        if (getIntent().hasExtra(Constants.INTENT_KEY)) {
            if (presenter != null)
                executeRequest(getIntent().getIntExtra(Constants.INTENT_KEY, 0));


        }
    }


    //recycler view initiation is handled once server response is ready
    @Override
    public void OnWeatherForecastReceived(WeatherForecast weatherForecast) {

        if (rv_forecast != null) {
            progressBar.setVisibility(View.GONE);
            adapter = new WeatherForecastAdapter(weatherForecast.getConsolidatedWeather());
            rv_forecast.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            rv_forecast.setLayoutManager(linearLayoutManager);

        }


    }

    //scenario where there is no network connection or issues in the response is handled. Given more time, would have created a separate component for showing
    //these errors instead of showing a toast and would have added more categories of error messages
    @Override
    public void OnError() {
        Toast.makeText(getApplicationContext(), Constants.SOMETHING_WENT_WRONG_PLEASE_CHECK_YOUR_NETWORK_CONNECTION_ONCE, Toast.LENGTH_LONG).show();

    }


    //request is executed only after checking network connectivity
    private void executeRequest(Integer id) {

        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            progressBar.setVisibility(View.VISIBLE);
            presenter.fetchDetails(id);

        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), Constants.UH_OH_PLEASE_CHECK_YOUR_NETWORK_CONNECTION, Toast.LENGTH_LONG).show();
        }

    }
}
