package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class Language implements Parcelable {

    public final String Code,Name;

    public Language(JSONObject obj) throws Twitter4HoloException {
        Code=getString(obj,"code");
        Name=getString(obj,"name");
    }

    public Language(Parcel in) {
        this.Code = in.readString();
        this.Name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Code);
        dest.writeString(this.Name);
    }

    @Override
    public String toString() {
        return "Language{" +
                "Code='" + Code + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Language)) return false;

        Language language = (Language) o;

        if (!Code.equals(language.Code)) return false;
        if (!Name.equals(language.Name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Code.hashCode();
        result = 31 * result + Name.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Language> CREATOR = new Parcelable.Creator<Language>() {
        public Language createFromParcel(Parcel source) {
            return new Language(source);
        }

        public Language[] newArray(int size) {
            return new Language[size];
        }
    };
}
