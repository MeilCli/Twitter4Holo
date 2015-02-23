package com.twitter.meil_mitu.twitter4holo.api.account;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class VerifyCredentials extends AbsGet {

    public VerifyCredentials(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public VerifyCredentials includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    public VerifyCredentials skipStatus(boolean skipStatus){
        addParam("skip_status",skipStatus);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/account/verify_credentials.json";
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
    public ResponseData<User> call() throws Twitter4HoloException {
        return Json.toUserResponseData(Oauth.get(this));
    }
}
