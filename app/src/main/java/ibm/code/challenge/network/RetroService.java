package ibm.code.challenge.network;


import java.util.List;

import ibm.code.challenge.models.WeatherForecast;
import ibm.code.challenge.models.WeatherInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroService {

    @GET("search/")
    Call<List<WeatherInfo>> getWeatherInfoList(@Query("lattlong") String coordinates);

    @GET("search/?")
    Call<List<WeatherInfo>> getLocation(@Query("query") String query);

    @GET("{location_id}")
    Call<WeatherForecast> getWeatherForecast(@Path("location_id") Integer id);

}
