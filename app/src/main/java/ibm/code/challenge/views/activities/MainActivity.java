package ibm.code.challenge.views.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ibm.code.challenge.R;
import ibm.code.challenge.presenters.WeatherListPresenter;
import ibm.code.challenge.utils.CommonUtils;
import ibm.code.challenge.utils.Constants;
import ibm.code.challenge.views.adapters.SearchSuggestionsAdapter;
import ibm.code.challenge.views.adapters.WeatherListAdapter;

public class MainActivity extends AppCompatActivity implements WeatherListPresenter.OnResponseListener, WeatherListAdapter.OnListItemClickListener, SearchSuggestionsAdapter.OnSuggestionClickListener {

    private static final int REQUEST_CODE = 123;
    private LocationManager locationManager;
    private String provider;
    private RecyclerView rv_weather;
    private RecyclerView rv_suggestions;
    private WeatherListAdapter weatherListAdapter;
    private SearchSuggestionsAdapter searchSuggestionsAdapter;
    private WeatherListPresenter weatherListPresenter;
    private ProgressBar progressBar;
    private AppCompatEditText editText;
    private SharedPreferences preferences;

    //setting up components and performing necessary requests
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpComponents();
        Location location = getLocation();

        if (location != null)
            executeRequest(location.getLatitude() + "," + location.getLongitude());
        else
            setUpEditTextView();

    }



    //Callback when the list items are returned from the server. Recycler view initialization is handled here
    @Override
    public void OnWeatherListReceived(List weatherInfo) {
        progressBar.setVisibility(View.GONE);

        if (rv_weather != null) {
            weatherListAdapter = new WeatherListAdapter(this, weatherInfo);
            rv_weather.setAdapter(weatherListAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            rv_weather.setLayoutManager(linearLayoutManager);
            rv_weather.setVisibility(View.VISIBLE);
            rv_suggestions.setVisibility(View.GONE);
            editText.setVisibility(View.GONE);

        }

    }

    //user's search queries are saved in preferences. Given more time, would have handled duplicate values in the suggestions
    //and also would have created a separate utility for shared preferences
    @Override
    public void saveToPreferences(String query) {
        if (preferences != null) {


            List<String> list;

            String storedValue = preferences.getString(Constants.SEARCH_TEXTS, Constants.DEFAULT);
            Gson gson = new Gson();
            if (Constants.DEFAULT.equalsIgnoreCase(storedValue)) {
                list = new ArrayList<>();
            } else list = gson.fromJson(storedValue, ArrayList.class);
            if (list != null) list.add(query);
            String searchTexts = gson.toJson(list);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Constants.SEARCH_TEXTS, searchTexts); // Storing string
            editor.apply();

        }

    }

    //scenario where no results were returned from the server call is handled. Given more time, would have created a separate component for showing
    //these errors instead of showing a toast
    @Override
    public void OnWeatherListNotFound() {
        Toast.makeText(getApplicationContext(), Constants.NO_RESULTS_FOUND_PLEASE_TRY_A_DIFFERENT_QUERY, Toast.LENGTH_LONG).show();
    }

    //scenario where there is no network connection or issues in the response is handled. Given more time, would have created a separate component for showing
    //these errors instead of showing a toast and would have added more categories of error messages
    @Override
    public void OnError() {
        Toast.makeText(getApplicationContext(), Constants.SOMETHING_WENT_WRONG_PLEASE_CHECK_YOUR_NETWORK_CONNECTION_ONCE, Toast.LENGTH_LONG).show();

    }


    //list item click handled here which opens up a new activity
    @Override
    public void onListItemClicked(Integer id) {

        Intent intent = new Intent(getApplicationContext(), WeatherForecastActivity.class);
        intent.putExtra(Constants.INTENT_KEY, id);
        startActivity(intent);

    }

    //search is performed when one of the search suggestions is clicked
    @Override
    public void onSuggestionClick(String text) {
        executeSearch(text);
    }


    //permission handling. Given more time, would have handled showing rationale to the user if he denied the permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    //components are set up. Given more time, would have used dagger for dependency injection
    private void setUpComponents() {
        rv_weather = (RecyclerView) findViewById(R.id.rv_weather);
        rv_suggestions = (RecyclerView) findViewById(R.id.rv_search_suggestions);
        progressBar = findViewById(R.id.progressBar);
        editText = findViewById(R.id.et_search);
        weatherListPresenter = new WeatherListPresenter(this);
        preferences = getApplicationContext().getSharedPreferences(Constants.SEARCH_HISTORY, 0);

    }


    //request is executed only after checking network connectivity
    private void executeRequest(String coordinates) {

        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            progressBar.setVisibility(View.VISIBLE);
            weatherListPresenter.fetchWeatherList(coordinates);
        } else {
            progressBar.setVisibility(View.GONE);

            Toast.makeText(getApplicationContext(), Constants.UH_OH_PLEASE_CHECK_YOUR_NETWORK_CONNECTION, Toast.LENGTH_LONG).show();
        }


    }

    //location is fetched from the device if the user has already provided permission, else permission is requested from the user again unless he declines seeing those pop-ups never again
    private Location getLocation() {
        Location location;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);


        //permission check
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE);

        if (provider == null) return null;

        location = locationManager.getLastKnownLocation(provider);
        return location;
    }

    private void setUpEditTextView() {
        editText.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        rv_weather.setVisibility(View.GONE);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    executeSearch(v.getText().toString());
                    return true;
                }
                return false;
            }
        });


        loadSuggestionsFromPreferences();
    }

    //suggestions loaded from preferences
    private void loadSuggestionsFromPreferences() {
        String storedValue = preferences.getString(Constants.SEARCH_TEXTS, Constants.DEFAULT);

        Gson gson = new Gson();
        ArrayList list;

        if (!Constants.DEFAULT.equalsIgnoreCase(storedValue)) {
            list = gson.fromJson(storedValue, ArrayList.class);

            if (rv_suggestions != null) {
                searchSuggestionsAdapter = new SearchSuggestionsAdapter(this, list);
                rv_suggestions.setAdapter(searchSuggestionsAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                rv_suggestions.setLayoutManager(linearLayoutManager);
                rv_suggestions.setVisibility(View.VISIBLE);
                rv_weather.setVisibility(View.GONE);
                editText.setVisibility(View.VISIBLE);

            }


        }
    }

    private void executeSearch(String text) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        weatherListPresenter.fetchLocation(text);
    }


}
