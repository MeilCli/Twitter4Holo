package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Retweets extends AbsGet<ITwitterJsonConverter>{

    private long id;

    public Retweets(AbsOauth oauth, ITwitterJsonConverter json, long id){
        super(oauth, json);
        this.id = id;
    }

    public Retweets count(int count){
        addParam("count", count);
        return this;
    }

    /**
     * must not use in JsonConverter for User
     */
    public Retweets trimUser(boolean trimUser){
        addParam("trim_user", trimUser);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/statuses/retweets/" + id + ".json";
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
    public ResponseList<Status> call() throws Twitter4HoloException{
        return Json.toStatusResponseList(Oauth.get(this));
    }
}
