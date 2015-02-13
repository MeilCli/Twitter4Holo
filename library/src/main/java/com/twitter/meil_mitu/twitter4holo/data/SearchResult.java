package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class SearchResult implements Parcelable {

    public final Status[] Statuses;

    public SearchResult(JSONObject obj) throws Twitter4HoloException {
        if(obj.isNull("statuses")){
            Statuses=new Status[0];
        }else {
            JSONArray ar=getJSONArray(obj,"statuses");
            int size=ar.length();
            Statuses=new Status[size];
            for(int i=0;i<size;i++){
                Statuses[i]=new Status(getJSONObject(ar,i));
            }
        }
    }

    public SearchResult(Parcel in) {
        Parcelable[] ps = in.readParcelableArray(Status.class.getClassLoader());
        if(ps==null){
            Statuses=new Status[0];
        }else{
            Statuses= Arrays.copyOf(ps,ps.length,Status[].class);
        }
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "Statuses=" + Arrays.toString(Statuses) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchResult)) return false;

        SearchResult that = (SearchResult) o;

        if (!Arrays.equals(Statuses, that.Statuses)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(Statuses);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelableArray(this.Statuses, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchResult> CREATOR = new Creator<SearchResult>() {
        public SearchResult createFromParcel(Parcel source) {
            return new SearchResult(source);
        }

        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };
}
