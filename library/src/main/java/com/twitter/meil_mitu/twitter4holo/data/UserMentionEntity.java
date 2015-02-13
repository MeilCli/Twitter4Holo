package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class UserMentionEntity extends Entity implements android.os.Parcelable {

    public final long Id;
    public final String ScreenName,Name;

    public UserMentionEntity(JSONObject obj) throws Twitter4HoloException {
        super(obj);
        Id=getLong(obj,"id");
        ScreenName=getString(obj,"screen_name");
        Name=getString(obj,"name");
    }

    public UserMentionEntity(Parcel in) {
        super(in);
        this.Id = in.readLong();
        this.ScreenName = in.readString();
        this.Name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeLong(this.Id);
        dest.writeString(this.ScreenName);
        dest.writeString(this.Name);
    }

    @Override
    public String toString() {
        return super.toString()+" UserMentionEntity{" +
                "Id=" + Id +
                ", ScreenName='" + ScreenName + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMentionEntity)) return false;
        if (!super.equals(o)) return false;

        UserMentionEntity that = (UserMentionEntity) o;

        if (Id != that.Id) return false;
        if (!Name.equals(that.Name)) return false;
        if (!ScreenName.equals(that.ScreenName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (Id ^ (Id >>> 32));
        result = 31 * result + ScreenName.hashCode();
        result = 31 * result + Name.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserMentionEntity> CREATOR = new Creator<UserMentionEntity>() {
        public UserMentionEntity createFromParcel(Parcel source) {
            return new UserMentionEntity(source);
        }

        public UserMentionEntity[] newArray(int size) {
            return new UserMentionEntity[size];
        }
    };
}
