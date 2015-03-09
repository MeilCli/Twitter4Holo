package com.twitter.meil_mitu.twitter4holo.api.users.suggestions;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.SuggestionUser;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Get extends AbsGet<ITwitterJsonConverter>{

    private String slug;

    public Get(AbsOauth oauth, ITwitterJsonConverter json, String slug){
        super(oauth, json);
        this.slug = slug;
    }

    public Get lang(String lang){
        addParam("lang", lang);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/users/suggestions/" + slug + ".json";
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
    public ResponseData<SuggestionUser> call() throws Twitter4HoloException{
        return Json.toSuggestionUserResponseData(Oauth.get(this));
    }
}
