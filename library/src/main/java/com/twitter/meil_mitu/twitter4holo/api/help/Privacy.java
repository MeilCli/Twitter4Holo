package com.twitter.meil_mitu.twitter4holo.api.help;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.PrivacyResult;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Privacy extends AbsGet<ITwitterJsonConverter>{

    public Privacy(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/help/privacy.json";
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
    public ResponseData<PrivacyResult> call() throws Twitter4HoloException{
        return Json.toPrivacyResultResponseData(Oauth.get(this));
    }
}
