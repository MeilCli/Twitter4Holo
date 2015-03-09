package com.twitter.meil_mitu.twitter4holo.aclog.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getInt;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getLong;

public class AclogUser implements Parcelable{

    public final int Count;
    public final long Id;

    public AclogUser(JSONObject obj) throws Twitter4HoloException{
        Count = getInt(obj, "count");
        Id = getLong(obj, "id");
    }

    public AclogUser(Parcel in){
        this.Count = in.readInt();
        this.Id = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.Count);
        dest.writeLong(this.Id);
    }

    @Override
    public String toString(){
        return "AclogUser{" +
                "Count=" + Count +
                ", Id=" + Id +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof AclogUser)) return false;

        AclogUser aclogUser = (AclogUser) o;

        if(Id != aclogUser.Id) return false;

        return true;
    }

    @Override
    public int hashCode(){
        return (int) (Id ^ (Id >>> 32));
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<AclogUser> CREATOR = new Parcelable.Creator<AclogUser>(){
        public AclogUser createFromParcel(Parcel source){
            return new AclogUser(source);
        }

        public AclogUser[] newArray(int size){
            return new AclogUser[size];
        }
    };
}
