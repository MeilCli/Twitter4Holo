package com.twitter.meil_mitu.twitter4holo.api.users.suggestions;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.Suggestion;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class List extends AbsGet {

    public List(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public List lang(String lang){
        addParam("lang",lang);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/users/suggestions.json";
    }

    @Override
    public int allowOauthType() {
        return OauthType.Oauth1|OauthType.Oauth2;
    }

    @Override
    public boolean isAuthorization() {
        return true;
    }

    @Override
    public ResponseList<Suggestion> call() throws Twitter4HoloException {
        return Json.toSuggestionResponseList(Oauth.get(this));
    }
}
