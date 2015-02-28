package com.twitter.meil_mitu.twitter4holo.api.friendships;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.IDs;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class NoRetweets extends AbsGet<ITwitterJsonConverter> {

    public NoRetweets(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    /**
     * must not use in JsonConverter for CursorIDs
     */
    public NoRetweets stringifyIds(boolean stringifyIds){
        addParam("stringify_ids",stringifyIds);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/friendships/no_retweets/ids.json";
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
    public ResponseData<IDs> call() throws Twitter4HoloException {
        return Json.toIDsResponseData(Oauth.get(this));
    }
}
