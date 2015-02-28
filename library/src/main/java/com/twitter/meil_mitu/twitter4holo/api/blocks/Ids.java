package com.twitter.meil_mitu.twitter4holo.api.blocks;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.CursorIDs;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Ids extends AbsGet<ITwitterJsonConverter> {

    public Ids(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    /**
     * must not use in JsonConverter for CursorIDs
     */
    public Ids stringifyIds(boolean stringifyIds){
        addParam("stringify_ids",stringifyIds);
        return this;
    }

    public Ids cursor(long cursor){
        addParam("cursor",cursor);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/blocks/ids.json";
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
