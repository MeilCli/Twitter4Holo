package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.item.UserItem;

import org.json.JSONObject;

import java.util.Date;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;
import static com.twitter.meil_mitu.twitter4holo.util.ParcelUtils.*;

public class User implements Parcelable {

    public final Date CreatedAt;
    public final String Description;
    public final UserEntities Entities;
    public final int FavouritesCount,FollowersCount,FriendsCount,ListedCount,StatusesCount;
    public final boolean IsFollowRequestSent,IsFollowing,IsProtected,IsMuting,IsVerified;
    public final long Id;
    public final String Lang,Location,Name,ProfileBackgroundImageUrl,ProfileBannerUrl
            ,ProfileImageUrl,ScreenName,Url;
    public final Status Status;
    public final UserEntitySupport UserEntitySupport;

    public User(JSONObject obj) throws Twitter4HoloException {
        CreatedAt=getDate(obj,"created_at");
        Description = getString(obj,"description","");
        if(obj.isNull("entities")){
            Entities=new UserEntities();
        }else{
            Entities=new UserEntities(getJSONObject(obj,"entities"));
        }
        FavouritesCount=getInt(obj,"favourites_count");
        IsFollowRequestSent=getBoolean(obj, "follow_request_sent", false);
        IsFollowing=getBoolean(obj,"following",false);
        FollowersCount=getInt(obj, "followers_count");
        FriendsCount=getInt(obj,"friends_count");
        Id=getLong(obj, "id");
        Lang=getString(obj, "lang", null);
        ListedCount=getInt(obj, "listed_count");
        Location=getString(obj, "location", "");
        IsMuting=getBoolean(obj,"muting",false);
        Name=getString(obj,"name");
        ProfileBackgroundImageUrl=getString(obj,"profile_background_image_url",null);
        ProfileBannerUrl=getString(obj,"profile_banner_url",null);
        ProfileImageUrl=getString(obj,"profile_image_url");
        IsProtected=getBoolean(obj,"protected");
        ScreenName=getString(obj,"screen_name");
        if(obj.isNull("status")){
            Status=null;
        }else{
            Status=new Status(getJSONObject(obj,"status"));
        }
        StatusesCount=getInt(obj,"statuses_count");
        Url=getString(obj,"url","");
        IsVerified=getBoolean(obj,"verified");
        UserEntitySupport=new UserEntitySupport(Url,Description,Entities);
    }

    public User(UserItem item){
        CreatedAt=item.CreatedAt;
        Description=item.Description;
        Entities=item.Entities;
        FavouritesCount=item.FavouritesCount;
        FollowersCount=item.FollowersCount;
        FriendsCount=item.FriendsCount;
        ListedCount=item.ListedCount;
        StatusesCount=item.StatusesCount;
        IsFollowRequestSent=item.IsFollowRequestSent;
        IsFollowing=item.IsFollowing;
        IsProtected=item.IsProtected;
        IsMuting=item.IsMuting;
        IsVerified=item.IsVerified;
        Id=item.Id;
        Lang=item.Lang;
        Location=item.Location;
        Name=item.Name;
        ProfileBackgroundImageUrl=item.ProfileBackgroundImageUrl;
        ProfileBannerUrl=item.ProfileBannerUrl;
                ProfileImageUrl=item.ProfileImageUrl;
        ScreenName=item.ScreenName;
        Url=item.Url;
        if(item.Status!=null){
            Status=new Status(item.Status);
        }else{
            Status=null;
        }
        UserEntitySupport=new UserEntitySupport(Url,Description,Entities);
    }

    public User(Parcel in) {
        long tmpCreatedAt = in.readLong();
        this.CreatedAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.Description = in.readString();
        this.Entities = in.readParcelable(UserEntities.class.getClassLoader());
        this.FavouritesCount = in.readInt();
        this.FollowersCount = in.readInt();
        this.FriendsCount = in.readInt();
        this.ListedCount = in.readInt();
        this.StatusesCount = in.readInt();
        this.IsFollowRequestSent = in.readByte() != 0;
        this.IsFollowing = in.readByte() != 0;
        this.IsProtected = in.readByte() != 0;
        this.IsVerified = in.readByte() != 0;
        this.Id = in.readLong();
        this.Lang = readNullableString(in);
        this.Location = in.readString();
        this.IsMuting= in.readByte() != 0;
        this.Name = in.readString();
        this.ProfileBackgroundImageUrl = readNullableString(in);
        this.ProfileBannerUrl = readNullableString(in);
        this.ProfileImageUrl = in.readString();
        this.ScreenName = in.readString();
        this.Url = in.readString();
        if(in.readByte()==1) {
            this.Status = in.readParcelable(com.twitter.meil_mitu.twitter4holo.data.Status.class.getClassLoader());
        }else{
            this.Status=null;
        }
        UserEntitySupport=new UserEntitySupport(Url,Description,Entities);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(CreatedAt != null ? CreatedAt.getTime() : -1);
        dest.writeString(this.Description);
        dest.writeParcelable(this.Entities, 0);
        dest.writeInt(this.FavouritesCount);
        dest.writeInt(this.FollowersCount);
        dest.writeInt(this.FriendsCount);
        dest.writeInt(this.ListedCount);
        dest.writeInt(this.StatusesCount);
        dest.writeByte(IsFollowRequestSent ? (byte) 1 : (byte) 0);
        dest.writeByte(IsFollowing ? (byte) 1 : (byte) 0);
        dest.writeByte(IsProtected ? (byte) 1 : (byte) 0);
        dest.writeByte(IsVerified ? (byte) 1 : (byte) 0);
        dest.writeLong(this.Id);
        writeNullableString(dest,this.Lang);
        dest.writeString(this.Location);
        dest.writeByte(IsMuting ? (byte) 1 : (byte) 0);
        dest.writeString(this.Name);
        writeNullableString(dest,this.ProfileBackgroundImageUrl);
        writeNullableString(dest,this.ProfileBannerUrl);
        dest.writeString(this.ProfileImageUrl);
        dest.writeString(this.ScreenName);
        dest.writeString(this.Url);
        dest.writeByte(this.Status!=null?(byte)1:(byte)0);
        if(this.Status!=null){
            dest.writeParcelable(this.Status, 0);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "CreatedAt=" + CreatedAt +
                ", Description='" + Description + '\'' +
                ", Entities=" + Entities +
                ", FavouritesCount=" + FavouritesCount +
                ", FollowersCount=" + FollowersCount +
                ", FriendsCount=" + FriendsCount +
                ", ListedCount=" + ListedCount +
                ", StatusesCount=" + StatusesCount +
                ", IsFollowRequestSent=" + IsFollowRequestSent +
                ", IsFollowing=" + IsFollowing +
                ", IsProtected=" + IsProtected +
                ", IsMuting=" + IsMuting +
                ", IsVerified=" + IsVerified +
                ", Id=" + Id +
                ", Lang='" + Lang + '\'' +
                ", Location='" + Location + '\'' +
                ", Name='" + Name + '\'' +
                ", ProfileBackgroundImageUrl='" + ProfileBackgroundImageUrl + '\'' +
                ", ProfileBannerUrl='" + ProfileBannerUrl + '\'' +
                ", ProfileImageUrl='" + ProfileImageUrl + '\'' +
                ", ScreenName='" + ScreenName + '\'' +
                ", Url='" + Url + '\'' +
                ", Status=" + Status +
                ", UserEntitySupport=" + UserEntitySupport +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (Id != user.Id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (Id ^ (Id >>> 32));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
