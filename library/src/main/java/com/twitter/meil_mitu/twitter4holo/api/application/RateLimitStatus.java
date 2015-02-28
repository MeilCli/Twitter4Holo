package com.twitter.meil_mitu.twitter4holo.api.application;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.RateLimitResult;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.util.Utils;

public class RateLimitStatus extends AbsGet<ITwitterJsonConverter> {

    public RateLimitStatus(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public RateLimitStatus resources(String[] resources){
        addParam("resources", Utils.toString(resources));
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/application/rate_limit_status.json";
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
    public ResponseData<RateLimitResult> call() throws Twitter4HoloException {
        return Json.toRateLimitResultResponseData(Oauth.get(this));
    }
}
