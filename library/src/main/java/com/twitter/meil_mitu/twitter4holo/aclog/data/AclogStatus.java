package com.twitter.meil_mitu.twitter4holo.aclog.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getInt;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONArray;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getLong;

public class AclogStatus implements Parcelable{

    public final long Id;
    public final long UserId;
    public final int FavoritesCount;
    public final int RetweetsCount;
    public final long[] Favoriters;
    public final long[] Retweeters;

    public AclogStatus(JSONObject obj) throws Twitter4HoloException{
        Id = getLong(obj, "id");
        UserId = getLong(obj, "user_id");
        FavoritesCount = getInt(obj, "favorites_count");
        RetweetsCount = getInt(obj, "retweets_count");
        if(obj.isNull("favoriters")){
            Favoriters = new long[0];
        }else{
            JSONArray ar = getJSONArray(obj, "favoriters");
            int size = ar.length();
            Favoriters = new long[size];
            for(int i = 0; i < size; i++){
                Favoriters[i] = getLong(ar, i);
            }
        }
        if(obj.isNull("retweeters")){
            Retweeters = new long[0];
        }else{
            JSONArray ar = getJSONArray(obj, "retweeters");
            int size = ar.length();
            Retweeters = new long[size];
            for(int i = 0; i < size; i++){
                Retweeters[i] = getLong(ar, i);
            }
        }
    }

    public AclogStatus(Parcel in){
        this.Id = in.readLong();
        this.UserId = in.readLong();
        this.FavoritesCount = in.readInt();
        this.RetweetsCount = in.readInt();
        this.Favoriters = in.createLongArray();
        this.Retweeters = in.createLongArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeLong(this.Id);
        dest.writeLong(this.UserId);
        dest.writeInt(this.FavoritesCount);
        dest.writeInt(this.RetweetsCount);
        dest.writeLongArray(this.Favoriters);
        dest.writeLongArray(this.Retweeters);
    }

    @Override
    public String toString(){
        return "AclogStatus{" +
                "Id=" + Id +
                ", UserId=" + UserId +
                ", FavoritesCount=" + FavoritesCount +
                ", RetweetsCount=" + RetweetsCount +
                ", Favoriters=" + Arrays.toString(Favoriters) +
                ", Retweeters=" + Arrays.toString(Retweeters) +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof AclogStatus)) return false;

        AclogStatus that = (AclogStatus) o;

        if(Id != that.Id) return false;
        if(UserId != that.UserId) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = (int) (Id ^ (Id >>> 32));
        result = 31 * result + (int) (UserId ^ (UserId >>> 32));
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<AclogStatus> CREATOR = new Parcelable.Creator<AclogStatus>(){
        public AclogStatus createFromParcel(Parcel source){
            return new AclogStatus(source);
        }

        public AclogStatus[] newArray(int size){
            return new AclogStatus[size];
        }
    };
}
