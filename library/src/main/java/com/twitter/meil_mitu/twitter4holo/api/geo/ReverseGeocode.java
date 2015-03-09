package com.twitter.meil_mitu.twitter4holo.api.geo;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.PlaceQuery;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class ReverseGeocode extends AbsGet<ITwitterJsonConverter>{

    public ReverseGeocode(AbsOauth oauth, ITwitterJsonConverter json, String latitude, String longitude){
        super(oauth, json);
        addParam("lat", latitude);
        addParam("long", longitude);
    }

    public ReverseGeocode accuracy(String accuracy){
        addParam("accuracy", accuracy);
        return this;
    }

    public ReverseGeocode granularity(String granularity){
        addParam("granularity", granularity);
        return this;
    }

    public ReverseGeocode maxResults(int maxResults){
        addParam("max_results", maxResults);
        return this;
    }

    public ReverseGeocode callback(String callback){
        addParam("callback", callback);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/geo/reverse_geocode.json";
    }

    @Override
    public int allowOauthType(){
        return OauthType.Oauth1;
    }

    @Override
    public boolean isAuthorization(){
        return true;
    }

    @Override
    public ResponseData<PlaceQuery> call() throws Twitter4HoloException{
        return Json.toPlaceQueryResponseData(Oauth.get(this));
    }
}
