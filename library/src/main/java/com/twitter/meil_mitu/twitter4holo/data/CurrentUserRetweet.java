package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.IObjectConverter;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class CurrentUserRetweet implements Parcelable ,IObjectConverter{
    public final long Id;

    public CurrentUserRetweet(JSONObject obj) throws Twitter4HoloException {
        Id = getLong(obj,"id");
    }

    public CurrentUserRetweet(Parcel in) {
        this.Id = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.Id);
    }

    @Override
    public JSONObject toJSONObject() throws Twitter4HoloException{
        JSONObject obj = new JSONObject();
        putLong(obj,"id",Id);
        return obj;
    }

    @Override
    public String toString() {
        return "CurrentUserRetweet{" +
                "Id=" + Id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrentUserRetweet)) return false;

        CurrentUserRetweet that = (CurrentUserRetweet) o;

        if (Id != that.Id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (Id ^ (Id >>> 32));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CurrentUserRetweet> CREATOR = new Creator<CurrentUserRetweet>() {
        public CurrentUserRetweet createFromParcel(Parcel source) {
            return new CurrentUserRetweet(source);
        }

        public CurrentUserRetweet[] newArray(int size) {
            return new CurrentUserRetweet[size];
        }
    };


}
