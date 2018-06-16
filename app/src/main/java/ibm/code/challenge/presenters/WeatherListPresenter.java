package ibm.code.challenge.presenters;

import java.util.List;

import ibm.code.challenge.models.WeatherInfo;
import ibm.code.challenge.network.RetroService;
import ibm.code.challenge.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class WeatherListPresenter {


    OnResponseListener onResponseListener;
    RetroService service;

    public WeatherListPresenter(OnResponseListener onResponseListener) {
        this.onResponseListener = onResponseListener;
        service = RetrofitInstance.getRetrofitInstance().create(RetroService.class);
    }

    public void fetchWeatherList(String coordinates) {


        Call<List<WeatherInfo>> call = service.getWeatherInfoList(coordinates);

        call.enqueue(new Callback<List<WeatherInfo>>() {
            @Override
            public void onResponse(Call<List<WeatherInfo>> call, Response<List<WeatherInfo>> response) {
                if (response.isSuccessful() && response.body() != null)
                    propagateResponse(response);
            }

            @Override
            public void onFailure(Call<List<WeatherInfo>> call, Throwable t) {

            }
        });

    }


    private void propagateResponse(Response<List<WeatherInfo>> response) {
        if (onResponseListener != null && response != null)
            onResponseListener.OnWeatherListReceived(response.body());
    }


    public void fetchLocation(final String query) {


        Call<List<WeatherInfo>> call = service.getLocation(query);

        call.enqueue(new Callback<List<WeatherInfo>>() {
            @Override
            public void onResponse(Call<List<WeatherInfo>> call, Response<List<WeatherInfo>> response) {

                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    onResponseListener.saveToPreferences(query);
                    propagateResponse(response);
                } else onResponseListener.OnWeatherListNotFound();

            }

            @Override
            public void onFailure(Call<List<WeatherInfo>> call, Throwable t) {
                onResponseListener.OnError();
            }
        });


    }


    public interface OnResponseListener {
        void OnWeatherListReceived(List WeatherInfo);

        void OnWeatherListNotFound();

        void OnError();

        void saveToPreferences(String query);


    }
}
