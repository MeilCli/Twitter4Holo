package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import java.util.Date;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class SavedSearch implements Parcelable {

    public final Date CreatedAt;
    public final long Id;
    public final String Name,Query;

    public SavedSearch(JSONObject obj) throws Twitter4HoloException {
        CreatedAt=getDate(obj,"created_at");
        Id=getLong(obj,"id");
        Name=getString(obj,"name");
        Query=getString(obj,"query");
    }

    public SavedSearch(Parcel in) {
        long tmpCreatedAt = in.readLong();
        this.CreatedAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.Id = in.readLong();
        this.Name = in.readString();
        this.Query = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(CreatedAt != null ? CreatedAt.getTime() : -1);
        dest.writeLong(this.Id);
        dest.writeString(this.Name);
        dest.writeString(this.Query);
    }

    @Override
    public String toString() {
        return "SavedSearch{" +
                "CreatedAt=" + CreatedAt +
                ", Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Query='" + Query + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SavedSearch)) return false;

        SavedSearch that = (SavedSearch) o;

        if (Id != that.Id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (Id ^ (Id >>> 32));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<SavedSearch> CREATOR = new Parcelable.Creator<SavedSearch>() {
        public SavedSearch createFromParcel(Parcel source) {
            return new SavedSearch(source);
        }

        public SavedSearch[] newArray(int size) {
            return new SavedSearch[size];
        }
    };
}
