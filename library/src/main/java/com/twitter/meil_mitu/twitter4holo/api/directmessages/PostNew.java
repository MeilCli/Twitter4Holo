package com.twitter.meil_mitu.twitter4holo.api.directmessages;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.DirectMessage;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class PostNew extends AbsPost {

    public PostNew(AbsOauth oauth, AbsJsonConverter json,String text) {
        super(oauth, json);
        addParam("text", text);
    }

    public PostNew userId(long userId){
        addParam("user_id",userId);
        return this;
    }

    public PostNew screenName(String screenName){
        addParam("screen_name",screenName);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/direct_messages/new.json";
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
    public DirectMessage call() throws Twitter4HoloException {
        return Json.toDirectMessage(Oauth.post(this));
    }
}
