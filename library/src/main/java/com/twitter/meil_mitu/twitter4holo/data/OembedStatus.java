package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class OembedStatus implements Parcelable {

    public final String Url,Type,Html;

    public OembedStatus(JSONObject obj) throws Twitter4HoloException {
        Url=getString(obj,"url");
        Type=getString(obj,"type");
        Html=getString(obj,"html");
    }

    public OembedStatus(Parcel in) {
        this.Url = in.readString();
        this.Type = in.readString();
        this.Html = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Url);
        dest.writeString(this.Type);
        dest.writeString(this.Html);
    }

    @Override
    public String toString() {
        return "OembedStatus{" +
                "Url='" + Url + '\'' +
                ", Type='" + Type + '\'' +
                ", Html='" + Html + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OembedStatus)) return false;

        OembedStatus that = (OembedStatus) o;

        if (!Html.equals(that.Html)) return false;
        if (!Type.equals(that.Type)) return false;
        if (!Url.equals(that.Url)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Url.hashCode();
        result = 31 * result + Type.hashCode();
        result = 31 * result + Html.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OembedStatus> CREATOR = new Creator<OembedStatus>() {
        public OembedStatus createFromParcel(Parcel source) {
            return new OembedStatus(source);
        }

        public OembedStatus[] newArray(int size) {
            return new OembedStatus[size];
        }
    };
}
