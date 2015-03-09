package com.twitter.meil_mitu.twitter4holo.activeandroid.serializer;

import com.activeandroid.serializer.TypeSerializer;
import com.twitter.meil_mitu.twitter4holo.data.UserEntities;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.toJSONObject;

public class UserEntitiesSerializer extends TypeSerializer{

    @Override
    public Class<?> getDeserializedType(){
        return UserEntities.class;
    }

    @Override
    public Class<?> getSerializedType(){
        return String.class;
    }

    @Override
    public String serialize(Object data){
        try{
            return ((UserEntities) data).toJSONObject().toString();
        }catch(Twitter4HoloException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserEntities deserialize(Object data){
        if(data == null){
            return new UserEntities();
        }
        try{
            return new UserEntities(toJSONObject((String) data));
        }catch(Twitter4HoloException e){
            e.printStackTrace();
            return new UserEntities();
        }
    }
}
