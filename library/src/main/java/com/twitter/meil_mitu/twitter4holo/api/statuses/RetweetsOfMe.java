package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class RetweetsOfMe extends AbsGet {

    public RetweetsOfMe(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public RetweetsOfMe sinceId(long sinceId){
        addParam("since_id",sinceId);
        return this;
    }

    public RetweetsOfMe maxId(long maxId){
        addParam("max_id",maxId);
        return this;
    }

    /**
     * must not use in JsonConverter for User
     */
    public RetweetsOfMe trimUser(boolean trimUser){
        addParam("trim_user",trimUser);
        return this;
    }

    public RetweetsOfMe includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    public RetweetsOfMe includeUserEntities(boolean includeUserEntities){
        addParam("include_user_entities",includeUserEntities);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/statuses/retweets_of_me.json";
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
    public ResponseList<Status> call() throws Twitter4HoloException {
        return Json.toStatusResponseList(Oauth.get(this));
    }
}
