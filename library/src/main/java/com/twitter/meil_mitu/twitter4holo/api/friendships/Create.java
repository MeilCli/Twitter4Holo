package com.twitter.meil_mitu.twitter4holo.api.friendships;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Create extends AbsPost {

    public Create(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Create screenName(String screenName){
        addParam("screen_name",screenName);
        return this;
    }

    public Create userId(long userId){
        addParam("user_id",userId);
        return this;
    }

    public Create follow(boolean follow){
        addParam("follow",follow);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/friendships/create.json";
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
    public User call() throws Twitter4HoloException {
        return Json.toUser(Oauth.post(this));
    }
}
