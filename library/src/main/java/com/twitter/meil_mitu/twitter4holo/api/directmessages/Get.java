package com.twitter.meil_mitu.twitter4holo.api.directmessages;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.DirectMessage;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Get extends AbsGet {

    public Get(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Get sinceId(long sinceId){
        addParam("since_id",sinceId);
        return this;
    }

    public Get maxId(long maxId){
        addParam("max_id",maxId);
        return this;
    }

    public Get count(int count){
        addParam("count",count);
        return this;
    }

    public Get includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    /**
     * must not use in JsonConverter for DirectMessage
     */
    public Get skipStatus(boolean skipStatus){
        addParam("skip_status",skipStatus);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/direct_messages.json";
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
    public ResponseList<DirectMessage> call() throws Twitter4HoloException {
        return Json.toDirectMessageResponseList(Oauth.get(this));
    }
}
