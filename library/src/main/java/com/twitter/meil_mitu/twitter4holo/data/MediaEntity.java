package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class MediaEntity extends URLEntity implements android.os.Parcelable{

    public final long Id;
    public final String MediaUrl, Type;
    public final VideoInfo VideoInfo;

    public MediaEntity(JSONObject obj) throws Twitter4HoloException{
        super(obj);
        Id = getLong(obj, "id");
        MediaUrl = getString(obj, "media_url");
        Type = getString(obj, "type");
        if(obj.isNull("video_info")){
            VideoInfo = null;
        }else{
            VideoInfo = new VideoInfo(getJSONObject(obj, "video_info"));
        }
    }

    public MediaEntity(Parcel in){
        super(in);
        this.Id = in.readLong();
        this.MediaUrl = in.readString();
        this.Type = in.readString();
        if(in.readByte() == 1){
            this.VideoInfo = in.readParcelable(com.twitter.meil_mitu.twitter4holo.data.VideoInfo.class.getClassLoader());
        }else{
            this.VideoInfo = null;
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        super.writeToParcel(dest, flags);
        dest.writeLong(this.Id);
        dest.writeString(this.MediaUrl);
        dest.writeString(this.Type);
        dest.writeByte(this.VideoInfo != null ? (byte) 1 : (byte) 0);
        if(this.VideoInfo != null){
            dest.writeParcelable(this.VideoInfo, flags);
        }
    }

    @Override
    public JSONObject toJSONObject() throws Twitter4HoloException{
        JSONObject obj = super.toJSONObject();
        putLong(obj,"id",Id);
        putString(obj,"media_url",MediaUrl);
        putString(obj,"type",Type);
        if(VideoInfo==null){
            putNull(obj,"video_info");
        }else{
            putJSONObject(obj,"video_info",VideoInfo.toJSONObject());
        }
        return obj;
    }

    @Override
    public String toString(){
        return super.toString() + " MediaEntity{" +
                "Id=" + Id +
                ", MediaUrl='" + MediaUrl + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof MediaEntity)) return false;
        if(!super.equals(o)) return false;

        MediaEntity that = (MediaEntity) o;

        if(Id != that.Id) return false;
        if(!MediaUrl.equals(that.MediaUrl)) return false;
        if(!Type.equals(that.Type)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = 31 * result + (int) (Id ^ (Id >>> 32));
        result = 31 * result + MediaUrl.hashCode();
        result = 31 * result + Type.hashCode();
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Creator<MediaEntity> CREATOR = new Creator<MediaEntity>(){
        public MediaEntity createFromParcel(Parcel source){
            return new MediaEntity(source);
        }

        public MediaEntity[] newArray(int size){
            return new MediaEntity[size];
        }
    };
}
