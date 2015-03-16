package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.item.StatusItem;

import org.json.JSONObject;

import java.util.Date;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getBoolean;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getDate;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getInt;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getLong;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getString;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.putNull;
import static com.twitter.meil_mitu.twitter4holo.util.ParcelUtils.readNullableString;
import static com.twitter.meil_mitu.twitter4holo.util.ParcelUtils.writeNullableString;
import static com.twitter.meil_mitu.twitter4holo.util.Utils.nullCheck;

public class Status implements Parcelable{

    public final Date CreatedAt;
    public final CurrentUserRetweet CurrentUserRetweet;
    public final Entities Entities, ExtendedEntities;
    public final int FavoriteCount, RetweetCount;
    public final boolean IsFavorited, IsRetweeted;
    public final long Id, InReplyToStatusId, InReplyToUserId;
    public final String InReplyToScreenName, Lang, Source, Text;
    public final Status RetweetedStatus;
    public final User User;
    public final EntitySupport EntitySupport;

    public Status(JSONObject obj) throws Twitter4HoloException{
        CreatedAt = getDate(obj, "created_at");
        if(obj.isNull("current_user_retweet")){
            CurrentUserRetweet = null;
        }else{
            CurrentUserRetweet = new CurrentUserRetweet(getJSONObject(obj, "current_user_retweet"));
        }
        if(obj.isNull("entities")){
            Entities = new Entities();
        }else{
            Entities = new Entities(getJSONObject(obj, "entities"));
        }
        if(obj.isNull("extended_entities")){
            ExtendedEntities = new Entities();
        }else{
            ExtendedEntities = new Entities(getJSONObject(obj, "extended_entities"));
        }
        FavoriteCount = getInt(obj, "favorite_count", 0);
        IsFavorited = getBoolean(obj, "favorited", false);
        Id = getLong(obj, "id");
        InReplyToScreenName = getString(obj, "in_reply_to_screen_name", null);
        InReplyToStatusId = getLong(obj, "in_reply_to_status_id", -1);
        InReplyToUserId = getLong(obj, "in_reply_to_user_id", -1);
        Lang = getString(obj, "lang", null);
        RetweetCount = getInt(obj, "retweet_count", 0);
        IsRetweeted = getBoolean(obj, "retweeted", false);
        if(obj.isNull("retweeted_status")){
            RetweetedStatus = null;
        }else{
            RetweetedStatus = new Status(getJSONObject(obj, "retweeted_status"));
        }
        Source = getString(obj, "source");
        Text = getString(obj, "text");
        if(obj.isNull("user")){
            User = null;
        }else{
            User = new User(getJSONObject(obj, "user"));
        }
        EntitySupport = new EntitySupport(Text, Entities);
    }

    public Status(StatusItem item){
        CreatedAt = item.CreatedAt;
        nullCheck(CreatedAt, "CreatedAt");
        CurrentUserRetweet = item.CurrentUserRetweet;
        Entities = item.Entities;
        nullCheck(Entities,"Entities");
        ExtendedEntities = item.ExtendedEntities;
        nullCheck(ExtendedEntities,"ExtendedEntities");
        FavoriteCount = item.FavoriteCount;
        RetweetCount = item.RetweetCount;
        IsFavorited = item.IsFavorited;
        IsRetweeted = item.IsRetweeted;
        Id = item.Id;
        InReplyToStatusId = item.InReplyToStatusId;
        InReplyToUserId = item.InReplyToUserId;
        InReplyToScreenName = item.InReplyToScreenName;
        Lang = item.Lang;
        Source = item.Source;
        nullCheck(Lang,"Lang");
        Text = item.Text;
        nullCheck(Text,"Text");
        if(item.RetweetedStatus != null){
            RetweetedStatus = new Status(item.RetweetedStatus);
        }else{
            RetweetedStatus = null;
        }
        if(item.User != null){
            User = new User(item.User);
        }else{
            User = null;
        }
        EntitySupport = new EntitySupport(Text, Entities);
    }

    public Status(Parcel in){
        long tmpCreatedAt = in.readLong();
        this.CreatedAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        if(in.readByte() == 1){
            this.CurrentUserRetweet = in.readParcelable(com.twitter.meil_mitu.twitter4holo.data.CurrentUserRetweet.class.getClassLoader());
        }else{
            this.CurrentUserRetweet = null;
        }
        this.Entities = in.readParcelable(com.twitter.meil_mitu.twitter4holo.data.Entities.class.getClassLoader());
        this.ExtendedEntities = in.readParcelable(com.twitter.meil_mitu.twitter4holo.data.Entities.class.getClassLoader());
        this.FavoriteCount = in.readInt();
        this.RetweetCount = in.readInt();
        this.IsFavorited = in.readByte() != 0;
        this.IsRetweeted = in.readByte() != 0;
        this.Id = in.readLong();
        this.InReplyToStatusId = in.readLong();
        this.InReplyToUserId = in.readLong();
        this.InReplyToScreenName = readNullableString(in);
        this.Lang = readNullableString(in);
        this.Source = in.readString();
        this.Text = in.readString();
        if(in.readByte() == 1){
            this.RetweetedStatus = in.readParcelable(Status.class.getClassLoader());
        }else{
            this.RetweetedStatus = null;
        }
        if(in.readByte() == 1){
            this.User = in.readParcelable(User.class.getClassLoader());
        }else{
            this.User = null;
        }
        EntitySupport = new EntitySupport(Text, Entities);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeLong(CreatedAt != null ? CreatedAt.getTime() : -1);
        dest.writeByte(this.CurrentUserRetweet != null ? (byte) 1 : (byte) 0);
        if(this.CurrentUserRetweet != null){
            dest.writeParcelable(this.CurrentUserRetweet, 0);
        }
        dest.writeParcelable(this.Entities, 0);
        dest.writeParcelable(this.ExtendedEntities, 0);
        dest.writeInt(this.FavoriteCount);
        dest.writeInt(this.RetweetCount);
        dest.writeByte(IsFavorited ? (byte) 1 : (byte) 0);
        dest.writeByte(IsRetweeted ? (byte) 1 : (byte) 0);
        dest.writeLong(this.Id);
        dest.writeLong(this.InReplyToStatusId);
        dest.writeLong(this.InReplyToUserId);
        writeNullableString(dest, this.InReplyToScreenName);
        writeNullableString(dest, this.Lang);
        dest.writeString(this.Source);
        dest.writeString(this.Text);
        dest.writeByte(this.RetweetedStatus != null ? (byte) 1 : (byte) 0);
        if(this.RetweetedStatus != null){
            dest.writeParcelable(this.RetweetedStatus, flags);
        }
        dest.writeByte(this.User != null ? (byte) 1 : (byte) 0);
        if(this.User != null){
            dest.writeParcelable(this.User, flags);
        }
    }

    @Override
    public String toString(){
        return "Status{" +
                "CreatedAt=" + CreatedAt +
                ", CurrentUserRetweet=" + CurrentUserRetweet +
                ", Entities=" + Entities +
                ", ExtendedEntities=" + ExtendedEntities +
                ", FavoriteCount=" + FavoriteCount +
                ", RetweetCount=" + RetweetCount +
                ", IsFavorited=" + IsFavorited +
                ", IsRetweeted=" + IsRetweeted +
                ", Id=" + Id +
                ", InReplyToStatusId=" + InReplyToStatusId +
                ", InReplyToUserId=" + InReplyToUserId +
                ", InReplyToScreenName='" + InReplyToScreenName + '\'' +
                ", Lang='" + Lang + '\'' +
                ", Source='" + Source + '\'' +
                ", Text='" + Text + '\'' +
                ", RetweetedStatus=" + RetweetedStatus +
                ", User=" + User +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Status)) return false;

        Status status = (Status) o;

        if(Id != status.Id) return false;
        if(RetweetedStatus != null ? !RetweetedStatus.equals(status.RetweetedStatus) : status.RetweetedStatus != null)
            return false;
        if(!User.equals(status.User)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = (int) (Id ^ (Id >>> 32));
        result = 31 * result + (RetweetedStatus != null ? RetweetedStatus.hashCode() : 0);
        result = 31 * result + User.hashCode();
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Creator<Status> CREATOR = new Creator<Status>(){
        public Status createFromParcel(Parcel source){
            return new Status(source);
        }

        public Status[] newArray(int size){
            return new Status[size];
        }
    };
}
