package com.twitter.meil_mitu.twitter4holo.aclog.api.tweets;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.aclog.AbsAclogGet;
import com.twitter.meil_mitu.twitter4holo.aclog.IAclogJsonConverter;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogStatus;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.util.ArrayList;

public class UserBest extends AbsAclogGet{

    private boolean isAuthorization;

    public UserBest(AbsOauth oauth, IAclogJsonConverter json){
        super(oauth, json);
    }

    public UserBest userId(long userId){
        addParam("user_id", userId);
        return this;
    }

    public UserBest screenName(String screenName){
        addParam("screen_name", screenName);
        return this;
    }

    public UserBest count(int count){
        addParam("count", count);
        return this;
    }

    public UserBest page(int page){
        addParam("page", page);
        return this;
    }

    public UserBest recent(String recent){
        addParam("recent", recent);
        return this;
    }

    public UserBest authorization(boolean isAuthorization){
        this.isAuthorization = isAuthorization;
        return this;
    }

    @Override
    public String url(){
        return Host + "/api/tweets/user_best.json";
    }

    @Override
    public int allowOauthType(){
        return OauthType.OauthEcho;
    }

    @Override
    public boolean isAuthorization(){
        return isAuthorization;
    }

    @Override
    public ArrayList<AclogStatus> call() throws Twitter4HoloException{
        return Json.toStatusList(Oauth.get(this));
    }
}
