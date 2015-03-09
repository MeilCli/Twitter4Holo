package com.twitter.meil_mitu.twitter4holo;

import com.squareup.okhttp.ResponseBody;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public abstract class AbsJsonConverter{

    protected JSONObject toJSONObject(String res) throws Twitter4HoloException{
        try{
            return new JSONObject(res);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    protected JSONArray toJSONArray(String res) throws Twitter4HoloException{
        try{
            return new JSONArray(res);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public String toString(ResponseBody body) throws Twitter4HoloException{
        try{
            return body.string();
        }catch(IOException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

}
