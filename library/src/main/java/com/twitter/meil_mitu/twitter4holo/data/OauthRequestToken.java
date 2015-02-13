package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

public class OauthRequestToken implements Parcelable {

    public final String OauthToken,OauthTokenSecret;

    public OauthRequestToken(String res){
        String oauthToken="";
        String oauthTokenSecret="";
        for(String s : res.split("&")) {
            if(s.startsWith("oauth_token=")==true) {
                oauthToken=s.substring(s.indexOf('=') + 1);
            } else if(s.startsWith("oauth_token_secret=")==true) {
                oauthTokenSecret=s.substring(s.indexOf('=') + 1);
            }
        }
        OauthToken=oauthToken;
        OauthTokenSecret=oauthTokenSecret;
    }

    public OauthRequestToken(Parcel in) {
        this.OauthToken = in.readString();
        this.OauthTokenSecret = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.OauthToken);
        dest.writeString(this.OauthTokenSecret);
    }

    @Override
    public String toString() {
        return "OauthRequestToken{" +
                "OauthToken='" + OauthToken + '\'' +
                ", OauthTokenSecret='" + OauthTokenSecret + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OauthRequestToken)) return false;

        OauthRequestToken that = (OauthRequestToken) o;

        if (!OauthToken.equals(that.OauthToken)) return false;
        if (!OauthTokenSecret.equals(that.OauthTokenSecret)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = OauthToken.hashCode();
        result = 31 * result + OauthTokenSecret.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OauthRequestToken> CREATOR = new Creator<OauthRequestToken>() {
        public OauthRequestToken createFromParcel(Parcel source) {
            return new OauthRequestToken(source);
        }

        public OauthRequestToken[] newArray(int size) {
            return new OauthRequestToken[size];
        }
    };
}
