package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class Cursor implements Parcelable {

    public final long PreviousCursor,NextCursor;

    public Cursor(JSONObject obj) throws Twitter4HoloException {
        PreviousCursor=getLong(obj,"previous_cursor");
        NextCursor=getLong(obj,"next_cursor");
    }

    public Cursor(Parcel in) {
        this.PreviousCursor = in.readLong();
        this.NextCursor = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.PreviousCursor);
        dest.writeLong(this.NextCursor);
    }

    @Override
    public String toString() {
        return "Cursor{" +
                "PreviousCursor=" + PreviousCursor +
                ", NextCursor=" + NextCursor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cursor)) return false;

        Cursor cursor = (Cursor) o;

        if (NextCursor != cursor.NextCursor) return false;
        if (PreviousCursor != cursor.PreviousCursor) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (PreviousCursor ^ (PreviousCursor >>> 32));
        result = 31 * result + (int) (NextCursor ^ (NextCursor >>> 32));
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
