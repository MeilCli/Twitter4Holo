package com.twitter.meil_mitu.twitter4holo.aclog.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getBoolean;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getInt;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getLong;

public class AclogStats implements Parcelable{

    public final long Id;//UserId
    public final int ReactionsCount;
    public final boolean IsRegistered;

    public AclogStats(JSONObject obj) throws Twitter4HoloException{
        Id = getLong(obj, "id");
        ReactionsCount = getInt(obj, "reactions_count");
        IsRegistered = getBoolean(obj, "registered");
    }

    public AclogStats(Parcel in){
        this.Id = in.readLong();
        this.ReactionsCount = in.readInt();
        this.IsRegistered = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeLong(this.Id);
        dest.writeInt(this.ReactionsCount);
        dest.writeByte(IsRegistered ? (byte) 1 : (byte) 0);
    }

    @Override
    public String toString(){
        return "AclogStats{" +
                "Id=" + Id +
                ", ReactionsCount=" + ReactionsCount +
                ", IsRegistered=" + IsRegistered +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof AclogStats)) return false;

        AclogStats that = (AclogStats) o;

        if(Id != that.Id) return false;

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

    public static final Parcelable.Creator<AclogStats> CREATOR = new Parcelable.Creator<AclogStats>(){
        public AclogStats createFromParcel(Parcel source){
            return new AclogStats(source);
        }

        public AclogStats[] newArray(int size){
            return new AclogStats[size];
        }
    };
}
