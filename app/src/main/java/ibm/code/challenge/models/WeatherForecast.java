
package ibm.code.challenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherForecast implements Parcelable {

    @SerializedName("consolidated_weather")
    @Expose
    private List<ConsolidatedWeather> consolidatedWeather = null;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("sun_rise")
    @Expose
    private String sunRise;
    @SerializedName("sun_set")
    @Expose
    private String sunSet;
    @SerializedName("timezone_name")
    @Expose
    private String timezoneName;
    @SerializedName("parent")
    @Expose
    private Parent parent;
    @SerializedName("sources")
    @Expose
    private List<Source> sources = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("woeid")
    @Expose
    private Integer id;
    @SerializedName("latt_long")
    @Expose
    private String coordinates;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    public final static Creator<WeatherForecast> CREATOR = new Creator<WeatherForecast>() {

        public WeatherForecast createFromParcel(Parcel in) {
            return new WeatherForecast(in);
        }

        public WeatherForecast[] newArray(int size) {
            return (new WeatherForecast[size]);
        }

    };

    protected WeatherForecast(Parcel in) {
        if (consolidatedWeather != null)
            in.readList(this.consolidatedWeather, (ConsolidatedWeather.class.getClassLoader()));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
        this.sunRise = ((String) in.readValue((String.class.getClassLoader())));
        this.sunSet = ((String) in.readValue((String.class.getClassLoader())));
        this.timezoneName = ((String) in.readValue((String.class.getClassLoader())));
        this.parent = ((Parent) in.readValue((Parent.class.getClassLoader())));
        in.readList(this.sources, (Source.class.getClassLoader()));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.locationType = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.coordinates = ((String) in.readValue((String.class.getClassLoader())));
        this.timezone = ((String) in.readValue((String.class.getClassLoader())));
    }

    public WeatherForecast() {
    }

    public List<ConsolidatedWeather> getConsolidatedWeather() {
        return consolidatedWeather;
    }

    public void setConsolidatedWeather(List<ConsolidatedWeather> consolidatedWeather) {
        this.consolidatedWeather = consolidatedWeather;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSunRise() {
        return sunRise;
    }

    public void setSunRise(String sunRise) {
        this.sunRise = sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public void setSunSet(String sunSet) {
        this.sunSet = sunSet;
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(consolidatedWeather);
        dest.writeValue(time);
        dest.writeValue(sunRise);
        dest.writeValue(sunSet);
        dest.writeValue(timezoneName);
        dest.writeValue(parent);
        dest.writeList(sources);
        dest.writeValue(title);
        dest.writeValue(locationType);
        dest.writeValue(id);
        dest.writeValue(coordinates);
        dest.writeValue(timezone);
    }

    public int describeContents() {
        return 0;
    }

}
