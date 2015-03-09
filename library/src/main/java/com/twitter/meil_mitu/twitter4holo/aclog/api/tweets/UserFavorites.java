package com.twitter.meil_mitu.twitter4holo.aclog.api.tweets;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.aclog.AbsAclogGet;
import com.twitter.meil_mitu.twitter4holo.aclog.IAclogJsonConverter;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogStatus;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.util.ArrayList;

public class UserFavorites extends AbsAclogGet{

    private boolean isAuthorization;

    public UserFavorites(AbsOauth oauth, IAclogJsonConverter json){
        super(oauth, json);
    }

    public UserFavorites userId(long userId){
        addParam("user_id", userId);
        return this;
    }

    public UserFavorites screenName(String screenName){
        addParam("screen_name", screenName);
        return this;
    }

    public UserFavorites count(int count){
        addParam("count", count);
        return this;
    }

    public UserFavorites page(int page){
        addParam("page", page);
        return this;
    }

    public UserFavorites reactions(int reactions){
        addParam("reactions", reactions);
        return this;
    }

    public UserFavorites authorization(boolean isAuthorization){
        this.isAuthorization = isAuthorization;
        return this;
    }

    @Override
    public String url(){
        return Host + "/api/tweets/user_favorites.json";
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
