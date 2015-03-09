package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.CursorIDs;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Retweeters extends AbsGet<ITwitterJsonConverter>{

    public Retweeters(AbsOauth oauth, ITwitterJsonConverter json, long id){
        super(oauth, json);
        addParam("id", id);
    }

    public Retweeters cursor(long cursor){
        addParam("cursor", cursor);
        return this;
    }

    /**
     * must not use in JsonConverter for CursorIDs
     */
    public Retweeters stringifyIds(boolean stringifyIds){
        addParam("stringify_ids", stringifyIds);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/statuses/retweeters/ids.json";
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
    public ResponseData<CursorIDs> call() throws Twitter4HoloException{
        return Json.toCursorIDsResponseData(Oauth.get(this));
    }
}
