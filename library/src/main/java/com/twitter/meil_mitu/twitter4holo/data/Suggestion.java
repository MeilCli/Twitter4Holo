package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getInt;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getString;

public class Suggestion implements Parcelable{

    public final String Name, Slug;
    public final int Size;

    public Suggestion(JSONObject obj) throws Twitter4HoloException{
        Name = getString(obj, "name");
        Slug = getString(obj, "slug");
        Size = getInt(obj, "size");
    }

    public Suggestion(Parcel in){
        this.Name = in.readString();
        this.Slug = in.readString();
        this.Size = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.Name);
        dest.writeString(this.Slug);
        dest.writeInt(this.Size);
    }

    @Override
    public String toString(){
        return "Suggestion{" +
                "Name='" + Name + '\'' +
                ", Slug='" + Slug + '\'' +
                ", Size=" + Size +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Suggestion)) return false;

        Suggestion that = (Suggestion) o;

        if(Size != that.Size) return false;
        if(!Name.equals(that.Name)) return false;
        if(!Slug.equals(that.Slug)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = Name.hashCode();
        result = 31 * result + Slug.hashCode();
        result = 31 * result + Size;
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Creator<Suggestion> CREATOR = new Creator<Suggestion>(){
        public Suggestion createFromParcel(Parcel source){
            return new Suggestion(source);
        }

        public Suggestion[] newArray(int size){
            return new Suggestion[size];
        }
    };

}
