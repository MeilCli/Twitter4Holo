package com.twitter.meil_mitu.twitter4holo.api.trends;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.TrendPlace;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Closest extends AbsGet<ITwitterJsonConverter>{

    public Closest(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public Closest latitude(String latitude){
        addParam("lat", latitude);
        return this;
    }

    public Closest longitude(String longitude){
        addParam("long", longitude);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/trends/closest.json";
    }

    @Override
    public int allowOauthType(){
        return OauthType.Oauth1 | OauthType.Oauth2;
    }

    @Override
    public boolean isAuthorization(){
        return true;
    }

    @Override
    public ResponseList<TrendPlace> call() throws Twitter4HoloException{
        return Json.toTrendPlaceResponseList(Oauth.get(this));
    }
}
