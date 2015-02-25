package com.twitter.meil_mitu.twitter4holo.api.help;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.Language;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Languages extends AbsGet {

    public Languages(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/help/languages.json";
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
    public ResponseList<Language> call() throws Twitter4HoloException {
        return Json.toLanguageResponseList(Oauth.get(this));
    }
}
