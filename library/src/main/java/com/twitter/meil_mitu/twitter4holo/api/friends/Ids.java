package com.twitter.meil_mitu.twitter4holo.api.friends;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.CursorIDs;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Ids extends AbsGet {

    public Ids(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Ids userId(long userId){
        addParam("user_id",userId);
        return this;
    }

    public Ids screenName(String screenName){
        addParam("screen_name",screenName);
        return this;
    }

    public Ids cursor(long cursor){
        addParam("cursor",cursor);
        return this;
    }

    /**
     * must not use in JsonConverter for CursorIDs
     */
    public Ids stringifyIds(boolean stringifyIds){
        addParam("stringify_ids",stringifyIds);
        return this;
    }

    public Ids count(int count){
        addParam("count",count);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/friends/ids.json";
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
    public ResponseData<CursorIDs> call() throws Twitter4HoloException {
        return Json.toCursorIDsResponseData(Oauth.get(this));
    }
}
