package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getString;

public class TosResult implements Parcelable{

    public final String Tos;

    public TosResult(JSONObject obj) throws Twitter4HoloException{
        Tos = getString(obj, "tos");
    }

    public TosResult(Parcel in){
        this.Tos = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.Tos);
    }

    @Override
    public String toString(){
        return "TosResult{" +
                "Tos='" + Tos + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof TosResult)) return false;

        TosResult tosResult = (TosResult) o;

        if(!Tos.equals(tosResult.Tos)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        return Tos.hashCode();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<TosResult> CREATOR = new Parcelable.Creator<TosResult>(){
        public TosResult createFromParcel(Parcel source){
            return new TosResult(source);
        }

        public TosResult[] newArray(int size){
            return new TosResult[size];
        }
    };
}
