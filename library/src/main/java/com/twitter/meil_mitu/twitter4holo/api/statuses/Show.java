package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Show extends AbsGet <ITwitterJsonConverter>{

    public Show(AbsOauth oauth, ITwitterJsonConverter json,long id) {
        super(oauth, json);
        addParam("id",id);
    }

    /**
     * must not use in JsonConverter for User
     */
    public Show trimUser(boolean trimUser){
        addParam("trim_user",trimUser);
        return this;
    }

    public Show includeMyRetweet(boolean includeMyRetweet){
        addParam("include_my_retweet",includeMyRetweet);
        return this;
    }

    public Show includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/statuses/show.json";
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
    public ResponseData<Status> call() throws Twitter4HoloException {
        return Json.toStatusResponseData(Oauth.get(this));
    }
}
