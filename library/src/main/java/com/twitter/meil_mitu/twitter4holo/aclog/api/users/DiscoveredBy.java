package com.twitter.meil_mitu.twitter4holo.aclog.api.users;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.aclog.AbsAclogGet;
import com.twitter.meil_mitu.twitter4holo.aclog.IAclogJsonConverter;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogUser;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.util.ArrayList;

public class DiscoveredBy extends AbsAclogGet{

    private boolean isAuthorization;

    public DiscoveredBy(AbsOauth oauth, IAclogJsonConverter json){
        super(oauth, json);
    }

    public DiscoveredBy id(long id){
        addParam("id", id);
        return this;
    }

    public DiscoveredBy screenName(String screenName){
        addParam("screen_name", screenName);
        return this;
    }

    public DiscoveredBy authorization(boolean isAuthorization){
        this.isAuthorization = isAuthorization;
        return this;
    }

    @Override
    public String url(){
        return Host + "/api/users/discovered_by.json";
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
    public ArrayList<AclogUser> call() throws Twitter4HoloException{
        return Json.toUserList(Oauth.get(this));
    }
}
