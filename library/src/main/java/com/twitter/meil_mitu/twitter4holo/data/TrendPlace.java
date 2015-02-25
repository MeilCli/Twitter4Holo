package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class TrendPlace implements Parcelable {

    public final String Country,CountryCode,Name;
    public final int Woeid;

    public TrendPlace(JSONObject obj) throws Twitter4HoloException {
        Country=getString(obj,"country","");
        CountryCode=getString(obj,"countryCode","");
        Name=getString(obj,"name","");
        Woeid=getInt(obj,"woeid");
    }

    public TrendPlace(Parcel in) {
        this.Country = in.readString();
        this.CountryCode = in.readString();
        this.Name = in.readString();
        this.Woeid = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Country);
        dest.writeString(this.CountryCode);
        dest.writeString(this.Name);
        dest.writeInt(this.Woeid);
    }

    @Override
    public String toString() {
        return "TrendPlace{" +
                "Country='" + Country + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", Name='" + Name + '\'' +
                ", Woeid=" + Woeid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrendPlace)) return false;

        TrendPlace that = (TrendPlace) o;

        if (Woeid != that.Woeid) return false;
        if (!Country.equals(that.Country)) return false;
        if (!CountryCode.equals(that.CountryCode)) return false;
        if (!Name.equals(that.Name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Country.hashCode();
        result = 31 * result + CountryCode.hashCode();
        result = 31 * result + Name.hashCode();
        result = 31 * result + Woeid;
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<TrendPlace> CREATOR = new Parcelable.Creator<TrendPlace>() {
        public TrendPlace createFromParcel(Parcel source) {
            return new TrendPlace(source);
        }

        public TrendPlace[] newArray(int size) {
            return new TrendPlace[size];
        }
    };
}
