
package ibm.code.challenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsolidatedWeather implements Parcelable {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("weather_state_name")
    @Expose
    private String weatherStateName;
    @SerializedName("weather_state_abbr")
    @Expose
    private String weatherStateAbbr;
    @SerializedName("wind_direction_compass")
    @Expose
    private String windDirectionCompass;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("applicable_date")
    @Expose
    private String applicableDate;
    @SerializedName("min_temp")
    @Expose
    private Float minTemp;
    @SerializedName("max_temp")
    @Expose
    private Float maxTemp;
    @SerializedName("the_temp")
    @Expose
    private Float theTemp;
    @SerializedName("wind_speed")
    @Expose
    private Float windSpeed;
    @SerializedName("wind_direction")
    @Expose
    private Float windDirection;
    @SerializedName("air_pressure")
    @Expose
    private Float airPressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("visibility")
    @Expose
    private Float visibility;
    @SerializedName("predictability")
    @Expose
    private Integer predictability;
    public final static Creator<ConsolidatedWeather> CREATOR = new Creator<ConsolidatedWeather>() {


        public ConsolidatedWeather createFromParcel(Parcel in) {
            return new ConsolidatedWeather(in);
        }

        public ConsolidatedWeather[] newArray(int size) {
            return (new ConsolidatedWeather[size]);
        }

    };

    protected ConsolidatedWeather(Parcel in) {
        this.id = ((Long) in.readValue((Integer.class.getClassLoader())));
        this.weatherStateName = ((String) in.readValue((String.class.getClassLoader())));
        this.weatherStateAbbr = ((String) in.readValue((String.class.getClassLoader())));
        this.windDirectionCompass = ((String) in.readValue((String.class.getClassLoader())));
        this.created = ((String) in.readValue((String.class.getClassLoader())));
        this.applicableDate = ((String) in.readValue((String.class.getClassLoader())));
        this.minTemp = ((Float) in.readValue((Float.class.getClassLoader())));
        this.maxTemp = ((Float) in.readValue((Float.class.getClassLoader())));
        this.theTemp = ((Float) in.readValue((Float.class.getClassLoader())));
        this.windSpeed = ((Float) in.readValue((Float.class.getClassLoader())));
        this.windDirection = ((Float) in.readValue((Float.class.getClassLoader())));
        this.airPressure = ((Float) in.readValue((Float.class.getClassLoader())));
        this.humidity = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.visibility = ((Float) in.readValue((Float.class.getClassLoader())));
        this.predictability = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ConsolidatedWeather() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeatherStateName() {
        return weatherStateName;
    }

    public void setWeatherStateName(String weatherStateName) {
        this.weatherStateName = weatherStateName;
    }

    public String getWeatherStateAbbr() {
        return weatherStateAbbr;
    }

    public void setWeatherStateAbbr(String weatherStateAbbr) {
        this.weatherStateAbbr = weatherStateAbbr;
    }

    public String getWindDirectionCompass() {
        return windDirectionCompass;
    }

    public void setWindDirectionCompass(String windDirectionCompass) {
        this.windDirectionCompass = windDirectionCompass;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getApplicableDate() {
        return applicableDate;
    }

    public void setApplicableDate(String applicableDate) {
        this.applicableDate = applicableDate;
    }

    public Float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Float minTemp) {
        this.minTemp = minTemp;
    }

    public Float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Float getTheTemp() {
        return theTemp;
    }

    public void setTheTemp(Float theTemp) {
        this.theTemp = theTemp;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Float getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Float windDirection) {
        this.windDirection = windDirection;
    }

    public Float getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(Float airPressure) {
        this.airPressure = airPressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Float getVisibility() {
        return visibility;
    }

    public void setVisibility(Float visibility) {
        this.visibility = visibility;
    }

    public Integer getPredictability() {
        return predictability;
    }

    public void setPredictability(Integer predictability) {
        this.predictability = predictability;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(weatherStateName);
        dest.writeValue(weatherStateAbbr);
        dest.writeValue(windDirectionCompass);
        dest.writeValue(created);
        dest.writeValue(applicableDate);
        dest.writeValue(minTemp);
        dest.writeValue(maxTemp);
        dest.writeValue(theTemp);
        dest.writeValue(windSpeed);
        dest.writeValue(windDirection);
        dest.writeValue(airPressure);
        dest.writeValue(humidity);
        dest.writeValue(visibility);
        dest.writeValue(predictability);
    }

    public int describeContents() {
        return 0;
    }

}
