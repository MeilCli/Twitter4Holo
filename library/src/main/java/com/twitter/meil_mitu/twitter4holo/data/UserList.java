package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.item.UserListItem;

import org.json.JSONObject;

import java.util.Date;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getBoolean;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getDate;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getInt;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getLong;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getString;
import static com.twitter.meil_mitu.twitter4holo.util.Utils.nullCheck;

public class UserList implements Parcelable{

    public final String Slug, Name, Mode, Description;
    public final Date CreatedAt;
    public final int SubscriberCount, MemberCount;
    public final long Id;
    public final boolean IsFollowing;
    public final User User;

    public UserList(JSONObject obj) throws Twitter4HoloException{
        Slug = getString(obj, "slug");
        Name = getString(obj, "name");
        CreatedAt = getDate(obj, "created_at");
        SubscriberCount = getInt(obj, "subscriber_count");
        MemberCount = getInt(obj, "member_count");
        Mode = getString(obj, "mode");
        Id = getLong(obj, "id");
        Description = getString(obj, "description");
        IsFollowing = getBoolean(obj, "following");
        if(obj.isNull("user")){
            throw new Twitter4HoloException("user is null");
        }else{
            User = new User(getJSONObject(obj, "user"));
        }
    }

    public UserList(UserListItem item){
        Slug = item.Slug;
        nullCheck(Slug,"Slug");
        Name = item.Name;
        nullCheck(Name,"Name");
        Mode = item.Mode;
        nullCheck(Mode,"Mode");
        Description = item.Description;
        nullCheck(Description,"Description");
        CreatedAt = item.CreatedAt;
        nullCheck(CreatedAt,"CreatedAt");
        SubscriberCount = item.SubscriberCount;
        MemberCount = item.MemberCount;
        Id = item.Id;
        IsFollowing = item.IsFollowing;
        nullCheck(item.User,"User");
        User = new User(item.User);
    }

    public UserList(Parcel in){
        this.Slug = in.readString();
        this.Name = in.readString();
        this.Mode = in.readString();
        this.Description = in.readString();
        long tmpCreatedAt = in.readLong();
        this.CreatedAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.SubscriberCount = in.readInt();
        this.MemberCount = in.readInt();
        this.Id = in.readLong();
        this.IsFollowing = in.readByte() != 0;
        this.User = in.readParcelable(com.twitter.meil_mitu.twitter4holo.data.User.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.Slug);
        dest.writeString(this.Name);
        dest.writeString(this.Mode);
        dest.writeString(this.Description);
        dest.writeLong(CreatedAt != null ? CreatedAt.getTime() : -1);
        dest.writeInt(this.SubscriberCount);
        dest.writeInt(this.MemberCount);
        dest.writeLong(this.Id);
        dest.writeByte(IsFollowing ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.User, 0);
    }

    @Override
    public String toString(){
        return "UserList{" +
                "Slug='" + Slug + '\'' +
                ", Name='" + Name + '\'' +
                ", Mode='" + Mode + '\'' +
                ", Description='" + Description + '\'' +
                ", CreatedAt=" + CreatedAt +
                ", SubscriberCount=" + SubscriberCount +
                ", MemberCount=" + MemberCount +
                ", Id=" + Id +
                ", IsFollowing=" + IsFollowing +
                ", User=" + User +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof UserList)) return false;

        UserList userList = (UserList) o;

        if(Id != userList.Id) return false;
        if(!User.equals(userList.User)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = (int) (Id ^ (Id >>> 32));
        result = 31 * result + User.hashCode();
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<UserList> CREATOR = new Parcelable.Creator<UserList>(){
        public UserList createFromParcel(Parcel source){
            return new UserList(source);
        }

        public UserList[] newArray(int size){
            return new UserList[size];
        }
    };
}
