package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class Setting implements Parcelable {

    public final String Language,ScreenName;
    public final boolean IsProtected;

    public Setting(JSONObject obj) throws Twitter4HoloException {
        Language=getString(obj,"language");
        IsProtected=getBoolean(obj,"protected");
        ScreenName=getString(obj,"screen_name");
    }

    public  Setting(Parcel in) {
        this.Language = in.readString();
        this.ScreenName = in.readString();
        this.IsProtected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Language);
        dest.writeString(this.ScreenName);
        dest.writeByte(IsProtected ? (byte) 1 : (byte) 0);
    }

    @Override
    public String toString() {
        return "Setting{" +
                "Language='" + Language + '\'' +
                ", ScreenName='" + ScreenName + '\'' +
                ", IsProtected=" + IsProtected +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Setting)) return false;

        Setting setting = (Setting) o;

        if (IsProtected != setting.IsProtected) return false;
        if (!Language.equals(setting.Language)) return false;
        if (!ScreenName.equals(setting.ScreenName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Language.hashCode();
        result = 31 * result + ScreenName.hashCode();
        result = 31 * result + (IsProtected ? 1 : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Setting> CREATOR = new Parcelable.Creator<Setting>() {
        public Setting createFromParcel(Parcel source) {
            return new Setting(source);
        }

        public Setting[] newArray(int size) {
            return new Setting[size];
        }
    };
}
