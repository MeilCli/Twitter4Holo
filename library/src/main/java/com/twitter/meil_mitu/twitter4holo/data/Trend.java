package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class Trend implements Parcelable {

    public final String Name,Query;

    public Trend(JSONObject obj) throws Twitter4HoloException {
        Name=getString(obj,"name");
        Query=getString(obj,"query");
    }

    public Trend(Parcel in) {
        this.Name = in.readString();
        this.Query = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeString(this.Query);
    }

    @Override
    public String toString() {
        return "Trend{" +
                "Name='" + Name + '\'' +
                ", Query='" + Query + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trend)) return false;

        Trend trend = (Trend) o;

        if (!Name.equals(trend.Name)) return false;
        if (!Query.equals(trend.Query)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Name.hashCode();
        result = 31 * result + Query.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Trend> CREATOR = new Parcelable.Creator<Trend>() {
        public Trend createFromParcel(Parcel source) {
            return new Trend(source);
        }

        public Trend[] newArray(int size) {
            return new Trend[size];
        }
    };
}
