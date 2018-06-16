package ibm.code.challenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherInfo implements Parcelable {

    @SerializedName("distance")
    @Expose
    private Integer distance;
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


    public WeatherInfo() {
    }

    protected WeatherInfo(Parcel in) {
        this.distance = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.locationType = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.coordinates = ((String) in.readValue((String.class.getClassLoader())));
    }


    public final static Parcelable.Creator<WeatherInfo> CREATOR = new Creator<WeatherInfo>() {

        public WeatherInfo createFromParcel(Parcel in) {
            return new WeatherInfo(in);
        }

        public WeatherInfo[] newArray(int size) {
            return (new WeatherInfo[size]);
        }

    };


    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(distance);
        dest.writeValue(title);
        dest.writeValue(locationType);
        dest.writeValue(id);
        dest.writeValue(coordinates);
    }

    public int describeContents() {
        return 0;
    }

}
