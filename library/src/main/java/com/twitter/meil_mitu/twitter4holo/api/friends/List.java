package com.twitter.meil_mitu.twitter4holo.api.friends;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.CursorUsers;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class List extends AbsGet {

    public List(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public List userId(long userId){
        addParam("user_id",userId);
        return this;
    }

    public List screenName(String screenName){
        addParam("screen_name",screenName);
        return this;
    }

    public List cursor(long cursor){
        addParam("cursor",cursor);
        return this;
    }

    public List count(int count){
        addParam("count",count);
        return this;
    }

    public List skipStatus(boolean skipStatus){
        addParam("skip_status",skipStatus);
        return this;
    }

    public List includeUserEntities(boolean includeUserEntities){
        addParam("include_user_entities",includeUserEntities);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/friends/list.json";
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
    public ResponseData<CursorUsers> call() throws Twitter4HoloException {
        return Json.toCursorUsersResponseData(Oauth.get(this));
    }
}
