package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.IObjectConverter;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class Entity implements Parcelable ,IObjectConverter{

    public final int Start,End;

    public Entity(JSONObject obj) throws Twitter4HoloException {
        JSONArray ar = getJSONArray(obj,"indices");
        Start=getInt(ar,0);
        End=getInt(ar,1);
    }

    public Entity(Parcel in) {
        this.Start = in.readInt();
        this.End = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Start);
        dest.writeInt(this.End);
    }

    @Override
    public JSONObject toJSONObject() throws Twitter4HoloException {
        JSONObject obj = new JSONObject();
        JSONArray ar = new JSONArray();
        putInt(ar,0,Start);
        putInt(ar,1,End);
        putJSONArray(obj,"indices",ar);
        return obj;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "Start=" + Start +
                ", End=" + End +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;

        Entity entity = (Entity) o;

        if (End != entity.End) return false;
        if (Start != entity.Start) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Start;
        result = 31 * result + End;
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Entity> CREATOR = new Creator<Entity>() {
        public Entity createFromParcel(Parcel source) {
            return new Entity(source);
        }

        public Entity[] newArray(int size) {
            return new Entity[size];
        }
    };

}
