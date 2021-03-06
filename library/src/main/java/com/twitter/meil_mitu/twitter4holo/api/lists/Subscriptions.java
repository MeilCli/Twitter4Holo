package com.twitter.meil_mitu.twitter4holo.api.lists;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.CursorLists;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Subscriptions extends AbsGet<ITwitterJsonConverter>{

    public Subscriptions(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public Subscriptions userId(long userId){
        addParam("user_id", userId);
        return this;
    }

    public Subscriptions screenName(String screenName){
        addParam("screen_name", screenName);
        return this;
    }

    public Subscriptions count(int count){
        addParam("count", count);
        return this;
    }

    public Subscriptions cursor(long cursor){
        addParam("cursor", cursor);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/lists/subscriptions.json";
    }

    @Override
    public int allowOauthType(){
        return OauthType.Oauth1 | OauthType.Oauth2;
    }

    @Override
    public boolean isAuthorization(){
        return true;
    }

    @Override
    public ResponseData<CursorLists> call() throws Twitter4HoloException{
        return Json.toCursorListsResponseData(Oauth.get(this));
    }
}
