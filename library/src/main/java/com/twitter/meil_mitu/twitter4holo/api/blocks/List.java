package com.twitter.meil_mitu.twitter4holo.api.blocks;

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

    public List includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    public List skipStatus(boolean skipStatus){
        addParam("skip_status",skipStatus);
        return this;
    }

    public List cursor(long cursor){
        addParam("cursor",cursor);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/blocks/list.json";
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
    public ResponseData<CursorUsers> call() throws Twitter4HoloException {
        return Json.toCursorUsersResponseData(Oauth.get(this));
    }
}
