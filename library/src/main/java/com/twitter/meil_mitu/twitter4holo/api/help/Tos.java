package com.twitter.meil_mitu.twitter4holo.api.help;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.TosResult;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Tos extends AbsGet<ITwitterJsonConverter>{

    public Tos(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/help/tos.json";
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
    public ResponseData<TosResult> call() throws Twitter4HoloException{
        return Json.toTosResultResponseData(Oauth.get(this));
    }
}
