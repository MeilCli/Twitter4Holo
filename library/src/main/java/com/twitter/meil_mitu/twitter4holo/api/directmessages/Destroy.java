package com.twitter.meil_mitu.twitter4holo.api.directmessages;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.DirectMessage;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Destroy extends AbsPost<ITwitterJsonConverter>{

    public Destroy(AbsOauth oauth, ITwitterJsonConverter json, long id){
        super(oauth, json);
        addParam("id", id);
    }

    public Destroy includeEntities(boolean includeEntities){
        addParam("include_entities", includeEntities);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/direct_messages/destroy.json";
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
    public DirectMessage call() throws Twitter4HoloException{
        return Json.toDirectMessage(Oauth.post(this));
    }
}
