package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

public class OauthToken implements Parcelable{

    public final String OauthToken, OauthTokenSecret, ScreenName, Name;
    public final long UserId;

    public OauthToken(String res){
        String oauthToken = "";
        String oauthTokenSecret = "";
        String userId = "";
        String screenName = "";
        String name = "";
        for(String s : res.split("&")){
            if(s.startsWith("oauth_token=") == true){
                oauthToken = s.substring(s.indexOf('=') + 1);
            }else if(s.startsWith("oauth_token_secret=") == true){
                oauthTokenSecret = s.substring(s.indexOf('=') + 1);
            }else if(s.startsWith("user_id=") == true){
                userId = s.substring(s.indexOf('=') + 1);
            }else if(s.startsWith("screen_name=") == true){
                screenName = s.substring(s.indexOf('=') + 1);
            }else if(s.startsWith("name=") == true){
                name = s.substring(s.indexOf('=') + 1);
            }
        }
        OauthToken = oauthToken;
        OauthTokenSecret = oauthTokenSecret;
        ScreenName = screenName;
        Name = name;
        UserId = Long.parseLong(userId);
    }

    public OauthToken(Parcel in){
        this.OauthToken = in.readString();
        this.OauthTokenSecret = in.readString();
        this.ScreenName = in.readString();
        this.Name = in.readString();
        this.UserId = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.OauthToken);
        dest.writeString(this.OauthTokenSecret);
        dest.writeString(this.ScreenName);
        dest.writeString(this.Name);
        dest.writeLong(this.UserId);
    }

    @Override
    public String toString(){
        return "OauthToken{" +
                "OauthToken='" + OauthToken + '\'' +
                ", OauthTokenSecret='" + OauthTokenSecret + '\'' +
                ", ScreenName='" + ScreenName + '\'' +
                ", Name='" + Name + '\'' +
                ", UserId=" + UserId +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof OauthToken)) return false;

        OauthToken that = (OauthToken) o;

        if(UserId != that.UserId) return false;
        if(!Name.equals(that.Name)) return false;
        if(!OauthToken.equals(that.OauthToken)) return false;
        if(!OauthTokenSecret.equals(that.OauthTokenSecret)) return false;
        if(!ScreenName.equals(that.ScreenName)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = OauthToken.hashCode();
        result = 31 * result + OauthTokenSecret.hashCode();
        result = 31 * result + ScreenName.hashCode();
        result = 31 * result + Name.hashCode();
        result = 31 * result + (int) (UserId ^ (UserId >>> 32));
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Creator<OauthToken> CREATOR = new Creator<OauthToken>(){
        public OauthToken createFromParcel(Parcel source){
            return new OauthToken(source);
        }

        public OauthToken[] newArray(int size){
            return new OauthToken[size];
        }
    };
}
