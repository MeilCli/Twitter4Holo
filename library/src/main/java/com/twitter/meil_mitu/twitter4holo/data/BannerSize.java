package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class BannerSize implements Parcelable {

    public final int H,W;
    public final String Url;

    public BannerSize(JSONObject obj) throws Twitter4HoloException {
        H=getInt(obj,"h");
        W=getInt(obj,"w");
        Url=getString(obj,"url");
    }

    public BannerSize(Parcel in) {
        this.H = in.readInt();
        this.W = in.readInt();
        this.Url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.H);
        dest.writeInt(this.W);
        dest.writeString(this.Url);
    }

    @Override
    public String toString() {
        return "BannerSize{" +
                "H=" + H +
                ", W=" + W +
                ", Url='" + Url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BannerSize)) return false;

        BannerSize that = (BannerSize) o;

        if (H != that.H) return false;
        if (W != that.W) return false;
        if (!Url.equals(that.Url)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = H;
        result = 31 * result + W;
        result = 31 * result + Url.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<BannerSize> CREATOR = new Parcelable.Creator<BannerSize>() {
        public BannerSize createFromParcel(Parcel source) {
            return new BannerSize(source);
        }

        public BannerSize[] newArray(int size) {
            return new BannerSize[size];
        }
    };
}
