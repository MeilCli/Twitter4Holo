package com.twitter.meil_mitu.twitter4holo.api.help;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.ConfigurationResult;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Configuration extends AbsGet {

    public Configuration(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/help/configuration.json";
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
    public ResponseData<ConfigurationResult> call() throws Twitter4HoloException {
        return Json.toConfigurationResultResponseData(Oauth.get(this));
    }
}
