package com.twitter.meil_mitu.twitter4holo.activeandroid.serializer;

import com.activeandroid.serializer.TypeSerializer;
import com.twitter.meil_mitu.twitter4holo.data.CurrentUserRetweet;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.toJSONObject;

public class CurrentUserRetweetSerializer extends TypeSerializer{

    @Override
    public Class<?> getDeserializedType(){
        return CurrentUserRetweet.class;
    }

    @Override
    public Class<?> getSerializedType(){
        return String.class;
    }

    @Override
    public String serialize(Object data){
        try{
            return ((CurrentUserRetweet) data).toJSONObject().toString();
        }catch(Twitter4HoloException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CurrentUserRetweet deserialize(Object data){
        if(data == null){
            return null;
        }
        try{
            return new CurrentUserRetweet(toJSONObject((String) data));
        }catch(Twitter4HoloException e){
            e.printStackTrace();
            return null;
        }
    }
}
