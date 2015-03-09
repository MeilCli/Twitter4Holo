package com.twitter.meil_mitu.twitter4holo.aclog.api.tweets;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.aclog.AbsAclogGet;
import com.twitter.meil_mitu.twitter4holo.aclog.IAclogJsonConverter;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogStatus;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.util.ArrayList;

public class UserFavoritedBy extends AbsAclogGet{

    private boolean isAuthorization;

    public UserFavoritedBy(AbsOauth oauth, IAclogJsonConverter json){
        super(oauth, json);
    }

    public UserFavoritedBy userId(long userId){
        addParam("user_id", userId);
        return this;
    }

    public UserFavoritedBy screenName(String screenName){
        addParam("screen_name", screenName);
        return this;
    }

    public UserFavoritedBy sourceUserId(long sourceUserId){
        addParam("source_user_id", sourceUserId);
        return this;
    }

    public UserFavoritedBy sourceScreenName(String sourceScreenName){
        addParam("source_screen_name", sourceScreenName);
        return this;
    }

    public UserFavoritedBy count(int count){
        addParam("count", count);
        return this;
    }

    public UserFavoritedBy page(int page){
        addParam("page", page);
        return this;
    }

    public UserFavoritedBy sinceId(long sinceId){
        addParam("since_id", sinceId);
        return this;
    }

    public UserFavoritedBy maxId(long maxId){
        addParam("max_id", maxId);
        return this;
    }

    public UserFavoritedBy reactions(int reactions){
        addParam("reactions", reactions);
        return this;
    }

    public UserFavoritedBy authorization(boolean isAuthorization){
        this.isAuthorization = isAuthorization;
        return this;
    }

    @Override
    public String url(){
        return Host + "/api/tweets/user_favorited_by.json";
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
