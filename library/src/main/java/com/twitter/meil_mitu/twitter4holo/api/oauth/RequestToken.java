package com.twitter.meil_mitu.twitter4holo.api.oauth;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.OauthRequestToken;
import com.twitter.meil_mitu.twitter4holo.exception.IncorrectException;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.oauth.Oauth;

public class RequestToken extends AbsPost {
    private Oauth oauth;

    public RequestToken(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
        if(Oauth instanceof Oauth){
            this.oauth=(Oauth)oauth;
        }else{
            throw new IncorrectException("Oauth is not Oauth");
        }
    }

    public RequestToken oauthCallback(String oauthCallback){
        addParam("oauth_callback",oauthCallback);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/oauth/request_token";
    }

    @Override
    public int allowOauthType() {
        return OauthType.Oauth1RequestToken;
    }

    @Override
    public boolean isAuthorization() {
        return true;
    }

    @Override
    public OauthRequestToken call() throws Twitter4HoloException {
        OauthRequestToken token = Json.toOauthRequestToken(Oauth.post(this));
        oauth.setAccessToken(token.OauthToken);
        oauth.setAccessTokenSecret(token.OauthTokenSecret);
        return token;
    }
}
