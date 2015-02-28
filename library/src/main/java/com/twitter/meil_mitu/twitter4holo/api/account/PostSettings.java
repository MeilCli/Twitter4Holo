package com.twitter.meil_mitu.twitter4holo.api.account;


import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.Setting;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;


public class PostSettings extends AbsPost <ITwitterJsonConverter>{

    public PostSettings(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public PostSettings trendLocationWoeid(int trendLocationWoeid){
        addParam("trend_location_woeid",trendLocationWoeid);
        return this;
    }

    public PostSettings sleepTimeEnabled(boolean sleepTimeEnabled){
        addParam("sleep_time_enabled",sleepTimeEnabled);
        return this;
    }

    public PostSettings startSleepTime(int startSleepTime){
        addParam("start_sleep_time",startSleepTime);
        return this;
    }

    public PostSettings endSleepTime(int endSleepTime){
        addParam("end_sleep_time",endSleepTime);
        return this;
    }

    public PostSettings timeZone(String timeZone){
        addParam("time_zone",timeZone);
        return this;
    }

    public PostSettings lang(String lang){
        addParam("lang",lang);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/account/settings.json";
    }

    @Override
    public int allowOauthType() {
        return OauthType.Oauth1;
    }

    @Override
    public boolean isAuthorization() {
        return true;
    }

    @Override
    public Setting call() throws Twitter4HoloException {
        return Json.toSetting(Oauth.post(this));
    }
}
