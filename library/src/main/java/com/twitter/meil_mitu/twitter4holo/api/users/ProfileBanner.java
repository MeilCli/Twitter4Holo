package com.twitter.meil_mitu.twitter4holo.api.users;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.Banner;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class ProfileBanner extends AbsGet<ITwitterJsonConverter>{

    public ProfileBanner(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public ProfileBanner userId(long userId){
        addParam("user_id", userId);
        return this;
    }

    public ProfileBanner screenName(String screenName){
        addParam("screen_name", screenName);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/users/profile_banner.json";
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
    public ResponseData<Banner> call() throws Twitter4HoloException{
        return Json.toBannerResponseData(Oauth.get(this));
    }
}
