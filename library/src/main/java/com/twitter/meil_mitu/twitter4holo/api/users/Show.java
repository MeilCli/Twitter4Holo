package com.twitter.meil_mitu.twitter4holo.api.users;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Show extends AbsGet {

    public Show(AbsOauth oauth, AbsJsonConverter json,long userId) {
        super(oauth, json);
        addParam("user_id",userId);
    }

    public Show(AbsOauth oauth,AbsJsonConverter json,String screenName){
        super(oauth,json);
        addParam("screen_name",screenName);
    }

    public Show includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/users/show.json";
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
    public ResponseData<User> call() throws Twitter4HoloException {
        return Json.toUserResponseData(Oauth.get(this));
    }
}
