package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getString;

public class Place implements Parcelable{

    public final String Country, CountryCode, FullName, Id, Name, PlaceType;

    public Place(JSONObject obj) throws Twitter4HoloException{
        Country = getString(obj, "country");
        CountryCode = getString(obj, "country_code");
        FullName = getString(obj, "full_name");
        Id = getString(obj, "id");
        Name = getString(obj, "name");
        PlaceType = getString(obj, "place_type");
    }

    public Place(Parcel in){
        this.Country = in.readString();
        this.CountryCode = in.readString();
        this.FullName = in.readString();
        this.Id = in.readString();
        this.Name = in.readString();
        this.PlaceType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.Country);
        dest.writeString(this.CountryCode);
        dest.writeString(this.FullName);
        dest.writeString(this.Id);
        dest.writeString(this.Name);
        dest.writeString(this.PlaceType);
    }

    @Override
    public String toString(){
        return "Place{" +
                "Country='" + Country + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", FullName='" + FullName + '\'' +
                ", Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", PlaceType='" + PlaceType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Place)) return false;

        Place place = (Place) o;

        if(!Id.equals(place.Id)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        return Id.hashCode();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>(){
        public Place createFromParcel(Parcel source){
            return new Place(source);
        }

        public Place[] newArray(int size){
            return new Place[size];
        }
    };
}
