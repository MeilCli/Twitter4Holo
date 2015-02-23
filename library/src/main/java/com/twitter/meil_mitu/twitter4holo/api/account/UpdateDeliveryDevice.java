package com.twitter.meil_mitu.twitter4holo.api.account;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class UpdateDeliveryDevice extends AbsPost {

    public UpdateDeliveryDevice(AbsOauth oauth, AbsJsonConverter json,String device) {
        super(oauth, json);
        addParam("device",device);
    }

    public UpdateDeliveryDevice includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/account/update_delivery_device.json";
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
    public Void call() throws Twitter4HoloException {
        // okhttp's connection is called close() in body().string()
        Json.toString(Oauth.post(this).body());
        return null;
    }
}
