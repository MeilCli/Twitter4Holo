package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.IObjectConverter;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class UserEntities implements Parcelable ,IObjectConverter{
    public final Entities Url,Description;

    public UserEntities(){
        Url=new Entities();
        Description=new Entities();
    }

    public UserEntities(JSONObject obj) throws Twitter4HoloException {
        if(obj.isNull("url")){
            Url=new Entities();
        }else{
            Url=new Entities(getJSONObject(obj,"url"));
        }
        if(obj.isNull("description")){
            Description=new Entities();
        }else{
            Description=new Entities(getJSONObject(obj,"description"));
        }
    }

    public UserEntities(Parcel in) {
        this.Url = in.readParcelable(Entities.class.getClassLoader());
        this.Description = in.readParcelable(Entities.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.Url, 0);
        dest.writeParcelable(this.Description, 0);
    }

    @Override
    public JSONObject toJSONObject() throws Twitter4HoloException{
        JSONObject obj = new JSONObject();
        putJSONObject(obj,"url",Url.toJSONObject());
        putJSONObject(obj,"description",Description.toJSONObject());
        return obj;
    }

    @Override
    public String toString() {
        return "UserEntities{" +
                "Url=" + Url +
                ", Description=" + Description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntities)) return false;

        UserEntities that = (UserEntities) o;

        if (Description != null ? !Description.equals(that.Description) : that.Description != null)
            return false;
        if (Url != null ? !Url.equals(that.Url) : that.Url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Url != null ? Url.hashCode() : 0;
        result = 31 * result + (Description != null ? Description.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserEntities> CREATOR = new Creator<UserEntities>() {
        public UserEntities createFromParcel(Parcel source) {
            return new UserEntities(source);
        }

        public UserEntities[] newArray(int size) {
            return new UserEntities[size];
        }
    };


}
