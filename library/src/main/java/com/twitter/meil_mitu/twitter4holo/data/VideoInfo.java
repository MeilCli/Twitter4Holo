package com.twitter.meil_mitu.twitter4holo.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.IObjectConverter;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONArray;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getLong;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.putJSONArray;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.putLong;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.toJSONArray;

public class VideoInfo implements Parcelable, IObjectConverter{

    public final long DurationMillis;
    public final VideoVariant[] Variants;

    public VideoInfo(JSONObject obj) throws Twitter4HoloException{
        DurationMillis = getLong(obj, "duration_millis");
        if(obj.isNull("variants")){
            Variants = new VideoVariant[0];
        }else{
            JSONArray ar = getJSONArray(obj, "variants");
            int size = ar.length();
            Variants = new VideoVariant[size];
            for(int i = 0; i < size; i++){
                Variants[i] = new VideoVariant(getJSONObject(ar, i));
            }
        }
    }

    public VideoInfo(Parcel in){
        this.DurationMillis = in.readLong();
        {
            Parcelable[] ps = in.readParcelableArray(VideoVariant.class.getClassLoader());
            if(ps == null){
                Variants = new VideoVariant[0];
            }else{
                Variants = Arrays.copyOf(ps, ps.length, VideoVariant[].class);
            }
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeLong(this.DurationMillis);
        dest.writeParcelableArray(this.Variants, flags);
    }

    @Override
    public JSONObject toJSONObject() throws Twitter4HoloException{
        JSONObject obj = new JSONObject();
        putLong(obj, "duration_millis", DurationMillis);
        putJSONArray(obj, "variants", toJSONArray(Variants));
        return obj;
    }

    @Override
    public String toString(){
        return "VideoInfo{" +
                "DurationMillis=" + DurationMillis +
                ", Variants=" + Arrays.toString(Variants) +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof VideoInfo)) return false;

        VideoInfo videoInfo = (VideoInfo) o;

        if(DurationMillis != videoInfo.DurationMillis) return false;
        if(!Arrays.equals(Variants, videoInfo.Variants)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = (int) (DurationMillis ^ (DurationMillis >>> 32));
        result = 31 * result + Arrays.hashCode(Variants);
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Creator<VideoInfo> CREATOR = new Creator<VideoInfo>(){
        public VideoInfo createFromParcel(Parcel source){
            return new VideoInfo(source);
        }

        public VideoInfo[] newArray(int size){
            return new VideoInfo[size];
        }
    };


}
