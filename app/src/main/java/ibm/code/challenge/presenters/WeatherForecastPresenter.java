package ibm.code.challenge.presenters;

import ibm.code.challenge.models.WeatherForecast;
import ibm.code.challenge.network.RetroService;
import ibm.code.challenge.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherForecastPresenter {

    OnForecastResponseListener onResponseListener;
    RetroService service;


    public WeatherForecastPresenter(OnForecastResponseListener onResponseListener) {
        this.onResponseListener = onResponseListener;
        service = RetrofitInstance.getRetrofitInstance().create(RetroService.class);
    }

    public void fetchDetails(final Integer id) {

        Call<WeatherForecast> call = service.getWeatherForecast(id);

        call.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                propagateResponse(response.body());
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                onResponseListener.OnError();
            }
        });


    }

    private void propagateResponse(WeatherForecast response) {
        if (onResponseListener != null && response != null)
            onResponseListener.OnWeatherForecastReceived(response);
    }

    public interface OnForecastResponseListener {
        void OnWeatherForecastReceived(WeatherForecast weatherForecast);

        void OnError();


    }
}
