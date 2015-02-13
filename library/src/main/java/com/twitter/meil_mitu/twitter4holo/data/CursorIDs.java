package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class CursorIDs extends Cursor implements android.os.Parcelable {

    public final long[] Ids;

    public CursorIDs(JSONObject obj) throws Twitter4HoloException {
        super(obj);
        if(obj.isNull("ids")){
            Ids=new long[0];
        }else{
            JSONArray ar = getJSONArray(obj,"ids");
            int size=ar.length();
            Ids=new long[size];
            for(int i=0;i<size;i++){
                Ids[i]=getLong(ar,i);
            }
        }
    }

    public CursorIDs(Parcel in) {
        super(in);
        this.Ids = in.createLongArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeLongArray(this.Ids);
    }

    @Override
    public String toString() {
        return super.toString()+" CursorIDs{" +
                "Ids=" + Arrays.toString(Ids) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CursorIDs)) return false;
        if (!super.equals(o)) return false;

        CursorIDs cursorIDs = (CursorIDs) o;

        if (!Arrays.equals(Ids, cursorIDs.Ids)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(Ids);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CursorIDs> CREATOR = new Creator<CursorIDs>() {
        public CursorIDs createFromParcel(Parcel source) {
            return new CursorIDs(source);
        }

        public CursorIDs[] newArray(int size) {
            return new CursorIDs[size];
        }
    };
}
