package com.twitter.meil_mitu.twitter4holo.aclog.api.tweets;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.aclog.AbsAclogGet;
import com.twitter.meil_mitu.twitter4holo.aclog.IAclogJsonConverter;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogStatus;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.util.ArrayList;

public class UserTimeline extends AbsAclogGet{

    private boolean isAuthorization;

    public UserTimeline(AbsOauth oauth, IAclogJsonConverter json){
        super(oauth, json);
    }

    public UserTimeline userId(long userId){
        addParam("user_id", userId);
        return this;
    }

    public UserTimeline screenName(String screenName){
        addParam("screen_name", screenName);
        return this;
    }

    public UserTimeline count(int count){
        addParam("count", count);
        return this;
    }

    public UserTimeline page(int page){
        addParam("page", page);
        return this;
    }

    public UserTimeline sinceId(long sinceId){
        addParam("since_id", sinceId);
        return this;
    }

    public UserTimeline maxId(long maxId){
        addParam("max_id", maxId);
        return this;
    }

    public UserTimeline reactions(int reactions){
        addParam("reactions", reactions);
        return this;
    }

    public UserTimeline authorization(boolean isAuthorization){
        this.isAuthorization = isAuthorization;
        return this;
    }

    @Override
    public String url(){
        return Host + "/api/tweets/user_timeline.json";
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
