package com.twitter.meil_mitu.twitter4holo.aclog.api.users;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.aclog.AbsAclogGet;
import com.twitter.meil_mitu.twitter4holo.aclog.IAclogJsonConverter;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogUser;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.util.ArrayList;

public class DiscoveredUsers extends AbsAclogGet{

    private boolean isAuthorization;

    public DiscoveredUsers(AbsOauth oauth, IAclogJsonConverter json){
        super(oauth, json);
    }

    public DiscoveredUsers id(long id){
        addParam("id", id);
        return this;
    }

    public DiscoveredUsers screenName(String screenName){
        addParam("screen_name", screenName);
        return this;
    }

    public DiscoveredUsers authorization(boolean isAuthorization){
        this.isAuthorization = isAuthorization;
        return this;
    }

    @Override
    public String url(){
        return Host + "/api/users/discovered_users.json";
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
