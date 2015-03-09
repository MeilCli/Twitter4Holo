package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.keys;

public class RateLimitResult implements Parcelable{

    public final HashMap<String, HashMap<String, RateLimit>> Resources;

    public RateLimitResult(JSONObject obj) throws Twitter4HoloException{
        if(obj.isNull("resources")){
            throw new Twitter4HoloException("resources is null");
        }else{
            obj = getJSONObject(obj, "resources");
        }
        Resources = new HashMap<String, HashMap<String, RateLimit>>();
        String api;
        JSONObject apiObject;
        HashMap<String, RateLimit> map;
        Iterator<String> endpoints;
        String endpoint;
        for(Iterator<String> apis = keys(obj); apis.hasNext(); ){
            api = apis.next();
            apiObject = getJSONObject(obj, api);
            map = new HashMap<String, RateLimit>();
            for(endpoints = keys(apiObject); endpoints.hasNext(); ){
                endpoint = endpoints.next();
                map.put(endpoint, new RateLimit(getJSONObject(apiObject, endpoint)));
            }
            Resources.put(api, map);
        }
    }

    public RateLimitResult(Parcel in){
        this.Resources = new HashMap<String, HashMap<String, RateLimit>>();
        int apiSize;
        String apiKey;
        HashMap<String, RateLimit> map;
        int endpointSize;
        String endpointKey;
        RateLimit rateLimit;
        apiSize = in.readInt();
        for(int i = 0; i < apiSize; i++){
            apiKey = in.readString();
            map = new HashMap<String, RateLimit>();
            endpointSize = in.readInt();
            for(int j = 0; j < endpointSize; j++){
                endpointKey = in.readString();
                rateLimit = in.readParcelable(RateLimit.class.getClassLoader());
                map.put(endpointKey, rateLimit);
            }
            this.Resources.put(apiKey, map);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(Resources.size());
        for(Map.Entry<String, HashMap<String, RateLimit>> api : Resources.entrySet()){
            dest.writeString(api.getKey());
            dest.writeInt(api.getValue().size());
            for(Map.Entry<String, RateLimit> endpoint : api.getValue().entrySet()){
                dest.writeString(endpoint.getKey());
                dest.writeParcelable(endpoint.getValue(), flags);
            }
        }
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<RateLimitResult> CREATOR = new Parcelable.Creator<RateLimitResult>(){
        public RateLimitResult createFromParcel(Parcel source){
            return new RateLimitResult(source);
        }

        public RateLimitResult[] newArray(int size){
            return new RateLimitResult[size];
        }
    };
}
