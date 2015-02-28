package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class HashtagEntity extends Entity implements android.os.Parcelable {

    public final String Text;

    public HashtagEntity(JSONObject obj) throws Twitter4HoloException {
        super(obj);
        Text=getString(obj,"text");
    }

    public HashtagEntity(Parcel in) {
        super(in);
        this.Text = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeString(this.Text);
    }

    @Override
    public JSONObject toJSONObject() throws Twitter4HoloException{
        JSONObject obj= super.toJSONObject();
        putString(obj,"text",Text);
        return obj;
    }

    @Override
    public String toString() {
        return super.toString()+" HashtagEntity{" +
                "Text='" + Text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashtagEntity)) return false;
        if (!super.equals(o)) return false;

        HashtagEntity that = (HashtagEntity) o;

        if (!Text.equals(that.Text)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Text.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HashtagEntity> CREATOR = new Creator<HashtagEntity>() {
        public HashtagEntity createFromParcel(Parcel source) {
            return new HashtagEntity(source);
        }

        public HashtagEntity[] newArray(int size) {
            return new HashtagEntity[size];
        }
    };
}
