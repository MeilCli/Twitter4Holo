package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class RelationshipSource extends RelationshipTarget implements android.os.Parcelable {

    public final boolean CanDm,IsBlocking,IsBlockedBy,IsMuting,WantRetweets,IsMarkedSpam;

    public RelationshipSource(JSONObject obj) throws Twitter4HoloException {
        super(obj);
        CanDm=getBoolean(obj,"can_dm");
        IsBlocking=getBoolean(obj,"blocking");
        IsBlockedBy=getBoolean(obj,"blocked_by");
        IsMuting=getBoolean(obj,"muting");
        WantRetweets=getBoolean(obj,"want_retweets");
        IsMarkedSpam=getBoolean(obj,"marked_spam");
    }

    public RelationshipSource(Parcel in) {
        super(in);
        this.CanDm = in.readByte() != 0;
        this.IsBlocking = in.readByte() != 0;
        this.IsBlockedBy = in.readByte() != 0;
        this.IsMuting = in.readByte() != 0;
        this.WantRetweets = in.readByte() != 0;
        this.IsMarkedSpam = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeByte(CanDm ? (byte) 1 : (byte) 0);
        dest.writeByte(IsBlocking ? (byte) 1 : (byte) 0);
        dest.writeByte(IsBlockedBy ? (byte) 1 : (byte) 0);
        dest.writeByte(IsMuting ? (byte) 1 : (byte) 0);
        dest.writeByte(WantRetweets ? (byte) 1 : (byte) 0);
        dest.writeByte(IsMarkedSpam ? (byte) 1 : (byte) 0);
    }

    @Override
    public String toString() {
        return super.toString()+" RelationshipSource{" +
                "CanDm=" + CanDm +
                ", IsBlocking=" + IsBlocking +
                ", IsBlockedBy=" + IsBlockedBy +
                ", IsMuting=" + IsMuting +
                ", WantRetweets=" + WantRetweets +
                ", IsMarkedSpam=" + IsMarkedSpam +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelationshipSource)) return false;
        if (!super.equals(o)) return false;

        RelationshipSource that = (RelationshipSource) o;

        if (CanDm != that.CanDm) return false;
        if (IsBlockedBy != that.IsBlockedBy) return false;
        if (IsBlocking != that.IsBlocking) return false;
        if (IsMarkedSpam != that.IsMarkedSpam) return false;
        if (IsMuting != that.IsMuting) return false;
        if (WantRetweets != that.WantRetweets) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (CanDm ? 1 : 0);
        result = 31 * result + (IsBlocking ? 1 : 0);
        result = 31 * result + (IsBlockedBy ? 1 : 0);
        result = 31 * result + (IsMuting ? 1 : 0);
        result = 31 * result + (WantRetweets ? 1 : 0);
        result = 31 * result + (IsMarkedSpam ? 1 : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RelationshipSource> CREATOR = new Creator<RelationshipSource>() {
        public RelationshipSource createFromParcel(Parcel source) {
            return new RelationshipSource(source);
        }

        public RelationshipSource[] newArray(int size) {
            return new RelationshipSource[size];
        }
    };
}
