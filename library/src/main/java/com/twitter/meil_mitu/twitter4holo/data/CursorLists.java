package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class CursorLists  extends Cursor implements android.os.Parcelable {

    public final UserList[] Lists;

    public CursorLists(JSONObject obj) throws Twitter4HoloException {
        super(obj);
        if(obj.isNull("lists")){
            Lists=new UserList[0];
        }else{
            JSONArray ar = getJSONArray(obj,"lists");
            int size=ar.length();
            Lists=new UserList[size];
            for(int i=0;i<size;i++){
                Lists[i]=new UserList(getJSONObject(ar,i));
            }
        }
    }

    public CursorLists(Parcel in) {
        super(in);
        Parcelable[] ps = in.readParcelableArray(UserList.class.getClassLoader());
        if(ps==null){
            Lists=new UserList[0];
        }else {
            Lists= Arrays.copyOf(ps, ps.length, UserList[].class);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeParcelableArray(this.Lists, flags);
    }

    @Override
    public String toString() {
        return super.toString()+" CursorLists{" +
                "Lists=" + Arrays.toString(Lists) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CursorLists)) return false;
        if (!super.equals(o)) return false;

        CursorLists that = (CursorLists) o;

        if (!Arrays.equals(Lists, that.Lists)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(Lists);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CursorLists> CREATOR = new Creator<CursorLists>() {
        public CursorLists createFromParcel(Parcel source) {
            return new CursorLists(source);
        }

        public CursorLists[] newArray(int size) {
            return new CursorLists[size];
        }
    };
}
