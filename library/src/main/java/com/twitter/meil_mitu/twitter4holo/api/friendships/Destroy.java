package com.twitter.meil_mitu.twitter4holo.api.friendships;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Destroy extends AbsPost<ITwitterJsonConverter>{

    public Destroy(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public Destroy screenName(String screenName){
        addParam("screen_name", screenName);
        return this;
    }

    public Destroy userId(long userId){
        addParam("user_id", userId);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/friendships/destroy.json";
    }

    @Override
    public int allowOauthType(){
        return OauthType.Oauth1;
    }

    @Override
    public boolean isAuthorization(){
        return true;
    }

    @Override
    public User call() throws Twitter4HoloException{
        return Json.toUser(Oauth.post(this));
    }
}
