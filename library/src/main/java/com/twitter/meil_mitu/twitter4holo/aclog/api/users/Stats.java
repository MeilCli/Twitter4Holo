package com.twitter.meil_mitu.twitter4holo.aclog.api.users;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.aclog.AbsAclogGet;
import com.twitter.meil_mitu.twitter4holo.aclog.IAclogJsonConverter;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogStats;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Stats extends AbsAclogGet{

    private boolean isAuthorization;

    public Stats(AbsOauth oauth, IAclogJsonConverter json){
        super(oauth, json);
    }

    public Stats id(long id){
        addParam("id", id);
        return this;
    }

    public Stats screenName(String screenName){
        addParam("screen_name", screenName);
        return this;
    }

    public Stats authorization(boolean isAuthorization){
        this.isAuthorization = isAuthorization;
        return this;
    }

    @Override
    public String url(){
        return Host + "/api/users/stats.json";
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
    public AclogStats call() throws Twitter4HoloException{
        return Json.toStats(Oauth.get(this));
    }
}
