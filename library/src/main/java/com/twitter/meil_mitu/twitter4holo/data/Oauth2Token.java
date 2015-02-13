package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class Oauth2Token implements Parcelable {
    public final String TokenType,AccessToken;

    public Oauth2Token(JSONObject obj) throws Twitter4HoloException {
        this.TokenType=getString(obj,"token_type");
        this.AccessToken=getString(obj,"access_token");
    }

    public Oauth2Token(JSONObject obj,String tokenType) throws Twitter4HoloException {
        this.TokenType=tokenType;
        this.AccessToken=getString(obj,"access_token");
    }

    public Oauth2Token(Parcel in) {
        this.TokenType = in.readString();
        this.AccessToken = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TokenType);
        dest.writeString(this.AccessToken);
    }

    @Override
    public String toString() {
        return "Oauth2Token{" +
                "TokenType='" + TokenType + '\'' +
                ", AccessToken='" + AccessToken + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Oauth2Token)) return false;

        Oauth2Token that = (Oauth2Token) o;

        if (!AccessToken.equals(that.AccessToken)) return false;
        if (!TokenType.equals(that.TokenType)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = TokenType.hashCode();
        result = 31 * result + AccessToken.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Oauth2Token> CREATOR = new Creator<Oauth2Token>() {
        public Oauth2Token createFromParcel(Parcel source) {
            return new Oauth2Token(source);
        }

        public Oauth2Token[] newArray(int size) {
            return new Oauth2Token[size];
        }
    };
}
