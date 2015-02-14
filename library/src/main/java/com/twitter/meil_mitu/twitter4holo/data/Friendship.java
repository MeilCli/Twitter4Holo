package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class Friendship implements Parcelable {

    public final String Name,ScreenName;
    public final long Id;
    public final String[] Connections;

    public Friendship(JSONObject obj) throws Twitter4HoloException {
        Name=getString(obj,"name");
        ScreenName=getString(obj,"screen_name");
        Id=getLong(obj,"id");
        if(obj.isNull("connections")){
            throw new Twitter4HoloException("connections is null");
        }
        JSONArray ar = getJSONArray(obj,"connections");
        int size = ar.length();
        Connections=new String[size];
        for(int i=0;i<size;i++){
            Connections[i]=getString(ar,i);
        }
    }

    public Friendship(Parcel in) {
        this.Name = in.readString();
        this.ScreenName = in.readString();
        this.Id = in.readLong();
        this.Connections = in.createStringArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeString(this.ScreenName);
        dest.writeLong(this.Id);
        dest.writeStringArray(this.Connections);
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "Name='" + Name + '\'' +
                ", ScreenName='" + ScreenName + '\'' +
                ", Id=" + Id +
                ", Connections=" + Arrays.toString(Connections) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friendship)) return false;

        Friendship that = (Friendship) o;

        if (Id != that.Id) return false;
        if (!Arrays.equals(Connections, that.Connections)) return false;
        if (!Name.equals(that.Name)) return false;
        if (!ScreenName.equals(that.ScreenName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Name.hashCode();
        result = 31 * result + ScreenName.hashCode();
        result = 31 * result + (int) (Id ^ (Id >>> 32));
        result = 31 * result + Arrays.hashCode(Connections);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Friendship> CREATOR = new Parcelable.Creator<Friendship>() {
        public Friendship createFromParcel(Parcel source) {
            return new Friendship(source);
        }

        public Friendship[] newArray(int size) {
            return new Friendship[size];
        }
    };
}
