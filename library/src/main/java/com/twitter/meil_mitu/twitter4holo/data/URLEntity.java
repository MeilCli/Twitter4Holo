package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class URLEntity extends Entity implements android.os.Parcelable {
    public final String Url,DisplayUrl,ExpandedUrl;

    public URLEntity(JSONObject obj) throws Twitter4HoloException {
        super(obj);
        Url=getString(obj,"url");
        DisplayUrl=getString(obj,"display_url");
        ExpandedUrl=getString(obj,"expanded_url");
    }

    public URLEntity(Parcel in) {
        super(in);
        this.Url = in.readString();
        this.DisplayUrl = in.readString();
        this.ExpandedUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeString(this.Url);
        dest.writeString(this.DisplayUrl);
        dest.writeString(this.ExpandedUrl);
    }

    @Override
    public String toString() {
        return super.toString()+" URLEntity{" +
                "Url='" + Url + '\'' +
                ", DisplayUrl='" + DisplayUrl + '\'' +
                ", ExpandedUrl='" + ExpandedUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof URLEntity)) return false;
        if (!super.equals(o)) return false;

        URLEntity urlEntity = (URLEntity) o;

        if (!DisplayUrl.equals(urlEntity.DisplayUrl)) return false;
        if (!ExpandedUrl.equals(urlEntity.ExpandedUrl)) return false;
        if (!Url.equals(urlEntity.Url)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Url.hashCode();
        result = 31 * result + DisplayUrl.hashCode();
        result = 31 * result + ExpandedUrl.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
