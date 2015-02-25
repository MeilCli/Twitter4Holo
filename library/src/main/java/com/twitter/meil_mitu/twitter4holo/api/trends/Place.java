package com.twitter.meil_mitu.twitter4holo.api.trends;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.TrendResult;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Place extends AbsGet {

    public Place(AbsOauth oauth, AbsJsonConverter json,int id) {
        super(oauth, json);
        addParam("id",id);
    }

    public Place exclude(String exclude){
        addParam("exclude",exclude);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/trends/place.json";
    }

    @Override
    public int allowOauthType() {
        return OauthType.Oauth1|OauthType.Oauth2;
    }

    @Override
    public boolean isAuthorization() {
        return true;
    }

    @Override
    public ResponseData<TrendResult> call() throws Twitter4HoloException {
        return Json.toTrendResultResponseData(Oauth.get(this));
    }
}
