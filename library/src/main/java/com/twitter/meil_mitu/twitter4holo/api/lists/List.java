package com.twitter.meil_mitu.twitter4holo.api.lists;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.UserList;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class List extends AbsGet<ITwitterJsonConverter>{

    public List(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public List userId(long userId){
        addParam("user_id", userId);
        return this;
    }

    public List screenName(String screenName){
        addParam("screen_name", screenName);
        return this;
    }

    public List reverse(boolean reverse){
        addParam("reverse", reverse);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/lists/list.json";
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
    public ResponseList<UserList> call() throws Twitter4HoloException{
        return Json.toUserListResponseList(Oauth.get(this));
    }
}
