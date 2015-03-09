package com.twitter.meil_mitu.twitter4holo.aclog.api.tweets;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.aclog.AbsAclogGet;
import com.twitter.meil_mitu.twitter4holo.aclog.IAclogJsonConverter;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogStatus;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.util.Utils;

import java.util.ArrayList;

public class Lookup extends AbsAclogGet{

    private boolean isAuthorization;

    public Lookup(AbsOauth oauth, IAclogJsonConverter json, long[] ids){
        super(oauth, json);
        addParam("ids", Utils.toString(ids));
    }

    public Lookup authorization(boolean isAuthorization){
        this.isAuthorization = isAuthorization;
        return this;
    }

    @Override
    public String url(){
        return Host + "/api/tweets/lookup.json";
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
    public ArrayList<AclogStatus> call() throws Twitter4HoloException{
        return Json.toStatusList(Oauth.get(this));
    }
}
