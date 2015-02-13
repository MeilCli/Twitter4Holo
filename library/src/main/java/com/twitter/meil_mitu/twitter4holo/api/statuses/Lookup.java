package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.util.Utils;

public class Lookup extends AbsGet {

    public Lookup(AbsOauth oauth, AbsJsonConverter json,long[] id) {
        super(oauth, json);
        addParam("id", Utils.toString(id));
    }

    public Lookup includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    /**
     * must not use in JsonConverter for User
     */
    public Lookup trimUser(boolean trimUser){
        addParam("trim_user",trimUser);
        return this;
    }

    /**
     * must not use in JsonConverter for Status
     */
    public Lookup map(boolean map){
        addParam("map",map);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/statuses/lookup.json";
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
    public ResponseList<Status> call() throws Twitter4HoloException {
        return Json.toStatusResponseList(Oauth.get(this));
    }
}
