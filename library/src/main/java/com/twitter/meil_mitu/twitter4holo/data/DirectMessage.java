package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.item.DirectMessageItem;

import org.json.JSONObject;

import java.util.Date;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class DirectMessage implements Parcelable {

    public final Date CreatedAt;
    public final Entities Entities;
    public final long Id;
    public final User Recipient,Sender;
    public final String Text;
    public final EntitySupport EntitySupport;

    public DirectMessage(JSONObject obj) throws Twitter4HoloException {
        CreatedAt=getDate(obj,"created_at");
        if(obj.isNull("entities")){
            Entities=new Entities();
        }else{
            Entities=new Entities(getJSONObject(obj,"entities"));
        }
        Id=getLong(obj,"id");
        if(obj.isNull("recipient")){
            throw new Twitter4HoloException("recipient is null");
        }else{
            Recipient=new User(getJSONObject(obj,"recipient"));
        }
        if(obj.isNull("sender")){
            throw new Twitter4HoloException("sender is null");
        }else{
            Sender=new User(getJSONObject(obj,"sender"));
        }
        Text=getString(obj,"text");
        EntitySupport=new EntitySupport(Text,Entities);
    }

    public DirectMessage(DirectMessageItem item){
       CreatedAt=item.CreatedAt;
        Entities=item.Entities;
       Id=item.Id;
        Recipient=new User(item.Recipient);
        Sender=new User(item.Sender);
        Text=item.Text;
        EntitySupport=new EntitySupport(Text,Entities);
    }

    public DirectMessage(Parcel in) {
        long tmpCreatedAt = in.readLong();
        this.CreatedAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.Entities = in.readParcelable(com.twitter.meil_mitu.twitter4holo.data.Entities.class.getClassLoader());
        this.Id = in.readLong();
        this.Recipient = in.readParcelable(User.class.getClassLoader());
        this.Sender = in.readParcelable(User.class.getClassLoader());
        this.Text = in.readString();
        this.EntitySupport = new EntitySupport(Text,Entities);
    }

    @Override
     public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(CreatedAt != null ? CreatedAt.getTime() : -1);
        dest.writeParcelable(this.Entities, 0);
        dest.writeLong(this.Id);
        dest.writeParcelable(this.Recipient, 0);
        dest.writeParcelable(this.Sender, 0);
        dest.writeString(this.Text);
    }

    @Override
    public String toString() {
        return "DirectMessage{" +
                "CreatedAt=" + CreatedAt +
                ", Entities=" + Entities +
                ", Id=" + Id +
                ", Recipient=" + Recipient +
                ", Sender=" + Sender +
                ", Text='" + Text + '\'' +
                ", EntitySupport=" + EntitySupport +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DirectMessage)) return false;

        DirectMessage that = (DirectMessage) o;

        if (Id != that.Id) return false;
        if (!Recipient.equals(that.Recipient)) return false;
        if (!Sender.equals(that.Sender)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (Id ^ (Id >>> 32));
        result = 31 * result + Recipient.hashCode();
        result = 31 * result + Sender.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DirectMessage> CREATOR = new Creator<DirectMessage>() {
        public DirectMessage createFromParcel(Parcel source) {
            return new DirectMessage(source);
        }

        public DirectMessage[] newArray(int size) {
            return new DirectMessage[size];
        }
    };
}
