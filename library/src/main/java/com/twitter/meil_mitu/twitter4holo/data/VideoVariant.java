package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.IObjectConverter;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getInt;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getString;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.putInt;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.putString;

public class VideoVariant implements Parcelable, IObjectConverter{

    public final int Bitrate;
    public final String ContentType, Url;

    public VideoVariant(JSONObject obj) throws Twitter4HoloException{
        Bitrate = getInt(obj, "bitrate", -1);
        ContentType = getString(obj, "content_type");
        Url = getString(obj, "url");
    }

    public VideoVariant(Parcel in){
        this.Bitrate = in.readInt();
        this.ContentType = in.readString();
        this.Url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.Bitrate);
        dest.writeString(this.ContentType);
        dest.writeString(this.Url);
    }

    @Override
    public JSONObject toJSONObject() throws Twitter4HoloException{
        JSONObject obj = new JSONObject();
        putInt(obj, "bitrate", Bitrate);
        putString(obj, "content_type", ContentType);
        putString(obj, "url", Url);
        return obj;
    }

    @Override
    public String toString(){
        return "VideoVariant{" +
                "Bitrate=" + Bitrate +
                ", ContentType='" + ContentType + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof VideoVariant)) return false;

        VideoVariant that = (VideoVariant) o;

        if(Bitrate != that.Bitrate) return false;
        if(!ContentType.equals(that.ContentType)) return false;
        if(!Url.equals(that.Url)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = Bitrate;
        result = 31 * result + ContentType.hashCode();
        result = 31 * result + Url.hashCode();
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Creator<VideoVariant> CREATOR = new Creator<VideoVariant>(){
        public VideoVariant createFromParcel(Parcel source){
            return new VideoVariant(source);
        }

        public VideoVariant[] newArray(int size){
            return new VideoVariant[size];
        }
    };
}
