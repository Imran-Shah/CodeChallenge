
package ibm.code.challenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parent implements Parcelable
{

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
    public final static Creator<Parent> CREATOR = new Creator<Parent>() {

        public Parent createFromParcel(Parcel in) {
            return new Parent(in);
        }

        public Parent[] newArray(int size) {
            return (new Parent[size]);
        }

    }
    ;

    protected Parent(Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.locationType = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.coordinates = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Parent() {
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
        dest.writeValue(title);
        dest.writeValue(locationType);
        dest.writeValue(id);
        dest.writeValue(coordinates);
    }

    public int describeContents() {
        return  0;
    }

}
