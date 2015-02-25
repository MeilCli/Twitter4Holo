package com.twitter.meil_mitu.twitter4holo.api.mutes.users;

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

    public Ids cursor(long cursor){
        addParam("cursor",cursor);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/mutes/users/ids.json";
    }

    @Override
    public int allowOauthType() {
        return OauthType.Oauth1;
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
