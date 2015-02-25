package com.twitter.meil_mitu.twitter4holo.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class PrivacyResult implements Parcelable {

    public final String Privacy;

    public PrivacyResult(JSONObject obj) throws Twitter4HoloException {
        Privacy=getString(obj,"privacy");
    }

    public PrivacyResult(Parcel in) {
        this.Privacy = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Privacy);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<PrivacyResult> CREATOR = new Parcelable.Creator<PrivacyResult>() {
        public PrivacyResult createFromParcel(Parcel source) {
            return new PrivacyResult(source);
        }

        public PrivacyResult[] newArray(int size) {
            return new PrivacyResult[size];
        }
    };
}
