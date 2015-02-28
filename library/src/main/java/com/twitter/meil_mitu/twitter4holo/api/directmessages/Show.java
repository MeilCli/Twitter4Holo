package com.twitter.meil_mitu.twitter4holo.api.directmessages;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.DirectMessage;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Show extends AbsGet<ITwitterJsonConverter> {

    public Show(AbsOauth oauth, ITwitterJsonConverter json,long id) {
        super(oauth, json);
        addParam("id",id);
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/direct_messages/show.json";
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
