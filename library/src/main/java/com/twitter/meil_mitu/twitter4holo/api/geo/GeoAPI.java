package com.twitter.meil_mitu.twitter4holo.api.geo;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;

public class GeoAPI extends AbsAPI<ITwitterJsonConverter>{

    public GeoAPI(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public Id id(String placeId){
        return new Id(Oauth, Json, placeId);
    }

    public ReverseGeocode reverseGeocode(String latitude, String longitude){
        return new ReverseGeocode(Oauth, Json, latitude, longitude);
    }

    public Search search(){
        return new Search(Oauth, Json);
    }

}
