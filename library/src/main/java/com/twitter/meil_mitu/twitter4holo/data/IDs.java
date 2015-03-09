package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;

import java.util.Arrays;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getLong;

public class IDs implements Parcelable{

    public final long[] Ids;

    public IDs(JSONArray ar) throws Twitter4HoloException{
        int size = ar.length();
        Ids = new long[size];
        for(int i = 0; i < size; i++){
            Ids[i] = getLong(ar, i);
        }
    }

    public IDs(Parcel in){
        this.Ids = in.createLongArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeLongArray(this.Ids);
    }

    @Override
    public String toString(){
        return "IDs{" +
                "Ids=" + Arrays.toString(Ids) +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof IDs)) return false;

        IDs iDs = (IDs) o;

        if(!Arrays.equals(Ids, iDs.Ids)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(Ids);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<IDs> CREATOR = new Parcelable.Creator<IDs>(){
        public IDs createFromParcel(Parcel source){
            return new IDs(source);
        }

        public IDs[] newArray(int size){
            return new IDs[size];
        }
    };
}
