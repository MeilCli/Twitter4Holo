package com.twitter.meil_mitu.twitter4holo.api.friendships;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.CursorIDs;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Outgoing extends AbsGet<ITwitterJsonConverter> {

    public Outgoing(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public Outgoing cursor(long cursor){
        addParam("cursor",cursor);
        return this;
    }

    /**
     * must not use in JsonConverter for CursorIDs
     */
    public Outgoing stringifyIds(boolean stringifyIds){
        addParam("stringify_ids",stringifyIds);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/friendships/outgoing.json";
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
