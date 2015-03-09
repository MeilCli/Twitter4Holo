package com.twitter.meil_mitu.twitter4holo.api.friendships;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.Relationship;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Show extends AbsGet<ITwitterJsonConverter>{

    public Show(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public Show sourceId(long sourceId){
        addParam("source_id", sourceId);
        return this;
    }

    public Show sourceScreenName(String sourceScreenName){
        addParam("source_screen_name", sourceScreenName);
        return this;
    }

    public Show targetId(long targetId){
        addParam("target_id", targetId);
        return this;
    }

    public Show targetScreenName(String targetScreenName){
        addParam("target_screen_name", targetScreenName);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/friendships/show.json";
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
    public ResponseData<Relationship> call() throws Twitter4HoloException{
        return Json.toRelationshipResponseData(Oauth.get(this));
    }
}
