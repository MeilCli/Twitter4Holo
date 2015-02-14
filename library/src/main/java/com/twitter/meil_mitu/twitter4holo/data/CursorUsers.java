package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class CursorUsers extends Cursor implements android.os.Parcelable {

    public final User[] Users;

    public CursorUsers(JSONObject obj) throws Twitter4HoloException {
        super(obj);
        if(obj.isNull("users")){
            Users=new User[0];
        }else{
            JSONArray ar = getJSONArray(obj,"users");
            int size=ar.length();
            Users=new User[size];
            for(int i=0;i<size;i++){
                Users[i]=new User(getJSONObject(ar,i));
            }
        }
    }

    public CursorUsers(Parcel in) {
        super(in);
        Parcelable[] ps = in.readParcelableArray(User.class.getClassLoader());
        if(ps==null){
            Users=new User[0];
        }else {
            Users= Arrays.copyOf(ps, ps.length,User[].class);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeParcelableArray(this.Users, flags);
    }

    @Override
    public String toString() {
        return super.toString()+" CursorUsers{" +
                "Users=" + Arrays.toString(Users) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CursorUsers)) return false;
        if (!super.equals(o)) return false;

        CursorUsers that = (CursorUsers) o;

        if (!Arrays.equals(Users, that.Users)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(Users);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CursorUsers> CREATOR = new Creator<CursorUsers>() {
        public CursorUsers createFromParcel(Parcel source) {
            return new CursorUsers(source);
        }

        public CursorUsers[] newArray(int size) {
            return new CursorUsers[size];
        }
    };
}
