package com.twitter.meil_mitu.twitter4holo.api.friendships;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.Friendship;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.util.Utils;

public class Lookup extends AbsGet<ITwitterJsonConverter>{

    public Lookup(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public Lookup screenName(String[] screenName){
        addParam("screen_name", Utils.toString(screenName));
        return this;
    }

    public Lookup userId(long[] userId){
        addParam("user_id", Utils.toString(userId));
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/friendships/lookup.json";
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
    public ResponseList<Friendship> call() throws Twitter4HoloException{
        return Json.toFriendshipResponseList(Oauth.get(this));
    }
}
