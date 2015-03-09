package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Destroy extends AbsPost<ITwitterJsonConverter>{

    private long id;

    public Destroy(AbsOauth oauth, ITwitterJsonConverter json, long id){
        super(oauth, json);
        this.id = id;
    }

    /**
     * must not use in JsonConverter for User
     */
    public Destroy trimUser(boolean trimUser){
        addParam("trim_user", trimUser);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/statuses/destroy/" + id + ".json";
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
    public Status call() throws Twitter4HoloException{
        return Json.toStatus(Oauth.post(this));
    }
}
