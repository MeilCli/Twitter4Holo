package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.IObjectConverter;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONArray;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.putJSONArray;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.toJSONArray;

public class Entities implements Parcelable, IObjectConverter{

    public final URLEntity[] URL;
    public final MediaEntity[] Media;
    public final HashtagEntity[] Hashtag;
    public final SymbolEntity[] Symbol;
    public final UserMentionEntity[] UserMention;

    public Entities(){
        URL = new URLEntity[0];
        Media = new MediaEntity[0];
        Hashtag = new HashtagEntity[0];
        Symbol = new SymbolEntity[0];
        UserMention = new UserMentionEntity[0];
    }

    public Entities(JSONObject obj) throws Twitter4HoloException{
        if(obj.isNull("urls")){
            URL = new URLEntity[0];
        }else{
            JSONArray ar = getJSONArray(obj, "urls");
            int size = ar.length();
            URL = new URLEntity[size];
            for(int i = 0; i < size; i++){
                URL[i] = new URLEntity(getJSONObject(ar, i));
            }
        }
        if(obj.isNull("media")){
            Media = new MediaEntity[0];
        }else{
            JSONArray ar = getJSONArray(obj, "media");
            int size = ar.length();
            Media = new MediaEntity[size];
            for(int i = 0; i < size; i++){
                Media[i] = new MediaEntity(getJSONObject(ar, i));
            }
        }
        if(obj.isNull("hashtags")){
            Hashtag = new HashtagEntity[0];
        }else{
            JSONArray ar = getJSONArray(obj, "hashtags");
            int size = ar.length();
            Hashtag = new HashtagEntity[size];
            for(int i = 0; i < size; i++){
                Hashtag[i] = new HashtagEntity(getJSONObject(ar, i));
            }
        }
        if(obj.isNull("symbols")){
            Symbol = new SymbolEntity[0];
        }else{
            JSONArray ar = getJSONArray(obj, "symbols");
            int size = ar.length();
            Symbol = new SymbolEntity[size];
            for(int i = 0; i < size; i++){
                Symbol[i] = new SymbolEntity(getJSONObject(ar, i));
            }
        }
        if(obj.isNull("user_mentions")){
            UserMention = new UserMentionEntity[0];
        }else{
            JSONArray ar = getJSONArray(obj, "user_mentions");
            int size = ar.length();
            UserMention = new UserMentionEntity[size];
            for(int i = 0; i < size; i++){
                UserMention[i] = new UserMentionEntity(getJSONObject(ar, i));
            }
        }
    }

    public Entities(Parcel in){
        {
            Parcelable[] ps = in.readParcelableArray(URLEntity.class.getClassLoader());
            if(ps == null){
                URL = new URLEntity[0];
            }else{
                URL = Arrays.copyOf(ps, ps.length, URLEntity[].class);
            }
        }
        {
            Parcelable[] ps = in.readParcelableArray(MediaEntity.class.getClassLoader());
            if(ps == null){
                Media = new MediaEntity[0];
            }else{
                Media = Arrays.copyOf(ps, ps.length, MediaEntity[].class);
            }
        }
        {
            Parcelable[] ps = in.readParcelableArray(HashtagEntity.class.getClassLoader());
            if(ps == null){
                Hashtag = new HashtagEntity[0];
            }else{
                Hashtag = Arrays.copyOf(ps, ps.length, HashtagEntity[].class);
            }
        }
        {
            Parcelable[] ps = in.readParcelableArray(SymbolEntity.class.getClassLoader());
            if(ps == null){
                Symbol = new SymbolEntity[0];
            }else{
                Symbol = Arrays.copyOf(ps, ps.length, SymbolEntity[].class);
            }
        }
        {
            Parcelable[] ps = in.readParcelableArray(UserMentionEntity.class.getClassLoader());
            if(ps == null){
                UserMention = new UserMentionEntity[0];
            }else{
                UserMention = Arrays.copyOf(ps, ps.length, UserMentionEntity[].class);
            }
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeParcelableArray(this.URL, flags);
        dest.writeParcelableArray(this.Media, flags);
        dest.writeParcelableArray(this.Hashtag, flags);
        dest.writeParcelableArray(this.Symbol, flags);
        dest.writeParcelableArray(this.UserMention, flags);
    }

    @Override
    public JSONObject toJSONObject() throws Twitter4HoloException{
        JSONObject obj = new JSONObject();
        putJSONArray(obj, "urls", toJSONArray(URL));
        putJSONArray(obj, "media", toJSONArray(Media));
        putJSONArray(obj, "hashtags", toJSONArray(Hashtag));
        putJSONArray(obj, "symbols", toJSONArray(Symbol));
        putJSONArray(obj, "user_mentions", toJSONArray(UserMention));
        return obj;
    }

    @Override
    public String toString(){
        return "Entities{" +
                "URL=" + Arrays.toString(URL) +
                ", Media=" + Arrays.toString(Media) +
                ", Hashtag=" + Arrays.toString(Hashtag) +
                ", Symbol=" + Arrays.toString(Symbol) +
                ", UserMention=" + Arrays.toString(UserMention) +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Entities)) return false;

        Entities entities = (Entities) o;

        if(!Arrays.equals(Hashtag, entities.Hashtag)) return false;
        if(!Arrays.equals(Media, entities.Media)) return false;
        if(!Arrays.equals(Symbol, entities.Symbol)) return false;
        if(!Arrays.equals(URL, entities.URL)) return false;
        if(!Arrays.equals(UserMention, entities.UserMention)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = Arrays.hashCode(URL);
        result = 31 * result + Arrays.hashCode(Media);
        result = 31 * result + Arrays.hashCode(Hashtag);
        result = 31 * result + Arrays.hashCode(Symbol);
        result = 31 * result + Arrays.hashCode(UserMention);
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Creator<Entities> CREATOR = new Creator<Entities>(){
        public Entities createFromParcel(Parcel source){
            return new Entities(source);
        }

        public Entities[] newArray(int size){
            return new Entities[size];
        }
    };


}
