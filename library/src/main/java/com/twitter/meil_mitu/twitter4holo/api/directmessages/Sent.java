package com.twitter.meil_mitu.twitter4holo.api.directmessages;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.DirectMessage;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Sent extends AbsGet <ITwitterJsonConverter>{

    public Sent(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public Sent sinceId(long sinceId){
        addParam("since_id",sinceId);
        return this;
    }

    public Sent maxId(long maxId){
        addParam("max_id",maxId);
        return this;
    }

    public Sent count(int count){
        addParam("count",count);
        return this;
    }

    public Sent page(int page){
        addParam("page",page);
        return this;
    }

    public Sent includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/direct_messages/sent.json";
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
    public ResponseData<DirectMessage> call() throws Twitter4HoloException {
        return Json.toDirectMessageResponseData(Oauth.get(this));
    }
}
