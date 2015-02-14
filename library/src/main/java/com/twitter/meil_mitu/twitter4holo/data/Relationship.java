package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class Relationship implements Parcelable {

    public final RelationshipSource Source;
    public final RelationshipTarget Target;

    public Relationship(JSONObject obj) throws Twitter4HoloException {
        if(obj.isNull("relationship")){
            throw new Twitter4HoloException("relationship is null");
        }else{
            obj=getJSONObject(obj,"relationship");
        }
        if(obj.isNull("source")){
            throw new Twitter4HoloException("source is null");
        }
        Source=new RelationshipSource(getJSONObject(obj,"source"));
        if(obj.isNull("target")){
            throw new Twitter4HoloException("target is null");
        }
        Target=new RelationshipTarget(getJSONObject(obj,"target"));
    }

    public Relationship(Parcel in) {
        this.Source = in.readParcelable(RelationshipSource.class.getClassLoader());
        this.Target = in.readParcelable(RelationshipTarget.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.Source, 0);
        dest.writeParcelable(this.Target, 0);
    }

    @Override
    public String toString() {
        return "Relationship{" +
                "Source=" + Source +
                ", Target=" + Target +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relationship)) return false;

        Relationship that = (Relationship) o;

        if (!Source.equals(that.Source)) return false;
        if (!Target.equals(that.Target)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Source.hashCode();
        result = 31 * result + Target.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Relationship> CREATOR = new Parcelable.Creator<Relationship>() {
        public Relationship createFromParcel(Parcel source) {
            return new Relationship(source);
        }

        public Relationship[] newArray(int size) {
            return new Relationship[size];
        }
    };
}
