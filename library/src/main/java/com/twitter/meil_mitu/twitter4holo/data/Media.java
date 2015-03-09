package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getLong;

public class Media implements Parcelable{

    public final long MediaId;

    public Media(JSONObject obj) throws Twitter4HoloException{
        MediaId = getLong(obj, "media_id");
    }

    public Media(Parcel in){
        this.MediaId = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeLong(this.MediaId);
    }

    @Override
    public String toString(){
        return "Media{" +
                "MediaId=" + MediaId +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Media)) return false;

        Media media = (Media) o;

        if(MediaId != media.MediaId) return false;

        return true;
    }

    @Override
    public int hashCode(){
        return (int) (MediaId ^ (MediaId >>> 32));
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Creator<Media> CREATOR = new Creator<Media>(){
        public Media createFromParcel(Parcel source){
            return new Media(source);
        }

        public Media[] newArray(int size){
            return new Media[size];
        }
    };
}
