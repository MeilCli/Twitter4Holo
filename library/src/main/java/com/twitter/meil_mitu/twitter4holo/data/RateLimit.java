package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.okhttp.Response;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getInt;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getLong;

public class RateLimit implements Parcelable{

    public final int Limit, Remaining;
    public final long Reset;

    public RateLimit(Response res){
        Limit = Integer.parseInt(res.header("X-Rate-Limit-Limit", "0"));
        Remaining = Integer.parseInt(res.header("X-Rate-Limit-Remaining", "0"));
        Reset = Long.parseLong(res.header("X-Rate-Limit-Reset", "-1"));
    }

    public RateLimit(JSONObject obj) throws Twitter4HoloException{
        Limit = getInt(obj, "limit");
        Remaining = getInt(obj, "remaining");
        Reset = getLong(obj, "reset");
    }

    public RateLimit(Parcel in){
        this.Limit = in.readInt();
        this.Remaining = in.readInt();
        this.Reset = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.Limit);
        dest.writeInt(this.Remaining);
        dest.writeLong(this.Reset);
    }

    @Override
    public String toString(){
        return "RateLimit{" +
                "Limit=" + Limit +
                ", Remaining=" + Remaining +
                ", Reset=" + Reset +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof RateLimit)) return false;

        RateLimit rateLimit = (RateLimit) o;

        if(Limit != rateLimit.Limit) return false;
        if(Remaining != rateLimit.Remaining) return false;
        if(Reset != rateLimit.Reset) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = Limit;
        result = 31 * result + Remaining;
        result = 31 * result + (int) (Reset ^ (Reset >>> 32));
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Creator<RateLimit> CREATOR = new Creator<RateLimit>(){
        public RateLimit createFromParcel(Parcel source){
            return new RateLimit(source);
        }

        public RateLimit[] newArray(int size){
            return new RateLimit[size];
        }
    };
}
