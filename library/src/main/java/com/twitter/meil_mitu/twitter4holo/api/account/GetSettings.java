package com.twitter.meil_mitu.twitter4holo.api.account;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.Setting;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class GetSettings extends AbsGet<ITwitterJsonConverter>{

    public GetSettings(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/account/settings.json";
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
    public ResponseData<Setting> call() throws Twitter4HoloException{
        return Json.toSettingResponseData(Oauth.get(this));
    }
}
