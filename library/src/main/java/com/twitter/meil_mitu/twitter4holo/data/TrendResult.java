package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Date;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getDate;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONArray;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;

public class TrendResult implements Parcelable{

    public final Date AsOf, CreatedAt;
    public final Trend[] Trends;

    public TrendResult(JSONObject obj) throws Twitter4HoloException{
        AsOf = getDate(obj, "as_of");
        CreatedAt = getDate(obj, "created_at");
        if(obj.isNull("trends")){
            throw new Twitter4HoloException("trends is null");
        }else{
            JSONArray ar = getJSONArray(obj, "trends");
            int size = ar.length();
            Trends = new Trend[size];
            for(int i = 0; i < size; i++){
                Trends[i] = new Trend(getJSONObject(ar, i));
            }
        }
    }

    public TrendResult(Parcel in){
        long tmpAsOf = in.readLong();
        this.AsOf = tmpAsOf == -1 ? null : new Date(tmpAsOf);
        long tmpCreatedAt = in.readLong();
        this.CreatedAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        Parcelable[] ps = in.readParcelableArray(Trend.class.getClassLoader());
        if(ps == null){
            this.Trends = new Trend[0];
        }else{
            this.Trends = Arrays.copyOf(ps, ps.length, Trend[].class);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeLong(AsOf != null ? AsOf.getTime() : -1);
        dest.writeLong(CreatedAt != null ? CreatedAt.getTime() : -1);
        dest.writeParcelableArray(this.Trends, flags);
    }

    @Override
    public String toString(){
        return "TrendResult{" +
                "AsOf=" + AsOf +
                ", CreatedAt=" + CreatedAt +
                ", Trends=" + Arrays.toString(Trends) +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof TrendResult)) return false;

        TrendResult that = (TrendResult) o;

        if(!AsOf.equals(that.AsOf)) return false;
        if(!CreatedAt.equals(that.CreatedAt)) return false;
        if(!Arrays.equals(Trends, that.Trends)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = AsOf.hashCode();
        result = 31 * result + CreatedAt.hashCode();
        result = 31 * result + Arrays.hashCode(Trends);
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<TrendResult> CREATOR = new Parcelable.Creator<TrendResult>(){
        public TrendResult createFromParcel(Parcel source){
            return new TrendResult(source);
        }

        public TrendResult[] newArray(int size){
            return new TrendResult[size];
        }
    };
}
