package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class ConfigurationResult implements Parcelable {

    public final int CharactersReservedPerMedia,ShortUrlLength,ShortUrlLengthHttps;
    public final String[] NonUsernamePaths;

    public ConfigurationResult(JSONObject obj) throws Twitter4HoloException {
        CharactersReservedPerMedia=getInt(obj,"characters_reserved_per_media");
        ShortUrlLength=getInt(obj,"short_url_length");
        ShortUrlLengthHttps=getInt(obj,"short_url_length_https");
        if(obj.isNull("non_username_paths")){
            throw new Twitter4HoloException("non_username_paths is null");
        }else{
            JSONArray ar = getJSONArray(obj,"non_username_paths");
            int size=ar.length();
            NonUsernamePaths=new String[size];
            for(int i=0;i<size;i++){
                NonUsernamePaths[i]=getString(ar,i);
            }
        }
    }

    public ConfigurationResult(Parcel in) {
        this.CharactersReservedPerMedia = in.readInt();
        this.ShortUrlLength = in.readInt();
        this.ShortUrlLengthHttps = in.readInt();
        this.NonUsernamePaths = in.createStringArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.CharactersReservedPerMedia);
        dest.writeInt(this.ShortUrlLength);
        dest.writeInt(this.ShortUrlLengthHttps);
        dest.writeStringArray(this.NonUsernamePaths);
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "CharactersReservedPerMedia=" + CharactersReservedPerMedia +
                ", ShortUrlLength=" + ShortUrlLength +
                ", ShortUrlLengthHttps=" + ShortUrlLengthHttps +
                ", NonUsernamePaths=" + Arrays.toString(NonUsernamePaths) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfigurationResult)) return false;

        ConfigurationResult that = (ConfigurationResult) o;

        if (CharactersReservedPerMedia != that.CharactersReservedPerMedia) return false;
        if (ShortUrlLength != that.ShortUrlLength) return false;
        if (ShortUrlLengthHttps != that.ShortUrlLengthHttps) return false;
        if (!Arrays.equals(NonUsernamePaths, that.NonUsernamePaths)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = CharactersReservedPerMedia;
        result = 31 * result + ShortUrlLength;
        result = 31 * result + ShortUrlLengthHttps;
        result = 31 * result + Arrays.hashCode(NonUsernamePaths);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ConfigurationResult> CREATOR = new Parcelable.Creator<ConfigurationResult>() {
        public ConfigurationResult createFromParcel(Parcel source) {
            return new ConfigurationResult(source);
        }

        public ConfigurationResult[] newArray(int size) {
            return new ConfigurationResult[size];
        }
    };
}
