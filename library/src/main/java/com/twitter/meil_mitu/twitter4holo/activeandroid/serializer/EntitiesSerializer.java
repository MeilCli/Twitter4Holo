package com.twitter.meil_mitu.twitter4holo.activeandroid.serializer;

import com.activeandroid.serializer.TypeSerializer;
import com.twitter.meil_mitu.twitter4holo.data.Entities;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.toJSONObject;

public class EntitiesSerializer extends TypeSerializer{

    @Override
    public Class<?> getDeserializedType(){
        return Entities.class;
    }

    @Override
    public Class<?> getSerializedType(){
        return String.class;
    }

    @Override
    public String serialize(Object data){
        try{
            return ((Entities) data).toJSONObject().toString();
        }catch(Twitter4HoloException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Entities deserialize(Object data){
        if(data == null){
            return new Entities();
        }
        try{
            return new Entities(toJSONObject((String) data));
        }catch(Twitter4HoloException e){
            e.printStackTrace();
            return new Entities();
        }
    }
}
