package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class RelationshipTarget implements Parcelable {

    public final long Id;
    public final String ScreenName;
    public final boolean IsFollowing,IsFollowedBy,IsFollowingReceived,IsFollowingRequested;

    public RelationshipTarget(JSONObject obj) throws Twitter4HoloException {
        Id=getLong(obj,"id");
        ScreenName=getString(obj,"screen_name");
        IsFollowing=getBoolean(obj,"following");
        IsFollowedBy=getBoolean(obj,"followed_by");
        IsFollowingReceived=getBoolean(obj,"following_received");
        IsFollowingRequested=getBoolean(obj,"following_requested");
    }

    public RelationshipTarget(Parcel in) {
        this.Id = in.readLong();
        this.ScreenName = in.readString();
        this.IsFollowing = in.readByte() != 0;
        this.IsFollowedBy = in.readByte() != 0;
        this.IsFollowingReceived = in.readByte() != 0;
        this.IsFollowingRequested = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.Id);
        dest.writeString(this.ScreenName);
        dest.writeByte(IsFollowing ? (byte) 1 : (byte) 0);
        dest.writeByte(IsFollowedBy ? (byte) 1 : (byte) 0);
        dest.writeByte(IsFollowingReceived ? (byte) 1 : (byte) 0);
        dest.writeByte(IsFollowingRequested ? (byte) 1 : (byte) 0);
    }

    @Override
    public String toString() {
        return "RelationshipTarget{" +
                "Id=" + Id +
                ", ScreenName='" + ScreenName + '\'' +
                ", IsFollowing=" + IsFollowing +
                ", IsFollowedBy=" + IsFollowedBy +
                ", IsFollowingReceived=" + IsFollowingReceived +
                ", IsFollowingRequested=" + IsFollowingRequested +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelationshipTarget)) return false;

        RelationshipTarget that = (RelationshipTarget) o;

        if (Id != that.Id) return false;
        if (IsFollowedBy != that.IsFollowedBy) return false;
        if (IsFollowing != that.IsFollowing) return false;
        if (IsFollowingReceived != that.IsFollowingReceived) return false;
        if (IsFollowingRequested != that.IsFollowingRequested) return false;
        if (!ScreenName.equals(that.ScreenName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (Id ^ (Id >>> 32));
        result = 31 * result + ScreenName.hashCode();
        result = 31 * result + (IsFollowing ? 1 : 0);
        result = 31 * result + (IsFollowedBy ? 1 : 0);
        result = 31 * result + (IsFollowingReceived ? 1 : 0);
        result = 31 * result + (IsFollowingRequested ? 1 : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
