package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONArray;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;

public class PlaceQuery implements Parcelable{

    public final Place[] Places;

    public PlaceQuery(JSONObject obj) throws Twitter4HoloException{
        if(obj.isNull("result")){
            throw new Twitter4HoloException("result is null");
        }else{
            obj = getJSONObject(obj, "result");
        }
        if(obj.isNull("places")){
            Places = new Place[0];
        }else{
            JSONArray ar = getJSONArray(obj, "places");
            int size = ar.length();
            Places = new Place[size];
            for(int i = 0; i < size; i++){
                Places[i] = new Place(getJSONObject(ar, i));
            }
        }
    }

    public PlaceQuery(Parcel in){
        Parcelable[] ps = in.readParcelableArray(Place.class.getClassLoader());
        if(ps == null){
            this.Places = new Place[0];
        }else{
            this.Places = Arrays.copyOf(ps, ps.length, Place[].class);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeParcelableArray(this.Places, flags);
    }

    @Override
    public String toString(){
        return "PlaceQuery{" +
                "Places=" + Arrays.toString(Places) +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof PlaceQuery)) return false;

        PlaceQuery that = (PlaceQuery) o;

        if(!Arrays.equals(Places, that.Places)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(Places);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<PlaceQuery> CREATOR = new Parcelable.Creator<PlaceQuery>(){
        public PlaceQuery createFromParcel(Parcel source){
            return new PlaceQuery(source);
        }

        public PlaceQuery[] newArray(int size){
            return new PlaceQuery[size];
        }
    };
}
