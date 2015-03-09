package com.twitter.meil_mitu.twitter4holo.api.oauth;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.OauthToken;
import com.twitter.meil_mitu.twitter4holo.exception.IncorrectException;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.oauth.Oauth;

public class AccessToken extends AbsPost<ITwitterJsonConverter>{

    private com.twitter.meil_mitu.twitter4holo.oauth.Oauth oauth;

    public AccessToken(AbsOauth oauth, ITwitterJsonConverter json, String oauthVerifier){
        super(oauth, json);
        if(Oauth instanceof Oauth){
            this.oauth = (Oauth) oauth;
        }else{
            throw new IncorrectException("Oauth is not Oauth");
        }
        if(oauthVerifier == null){
            throw new NullPointerException("oauthVerifier is null");
        }
        addParam("oauth_verifier", oauthVerifier);
    }

    @Override
    public String url(){
        return "https://api.twitter.com/oauth/access_token";
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
    public OauthToken call() throws Twitter4HoloException{
        OauthToken token = Json.toOauthToken(Oauth.post(this));
        oauth.setAccessToken(token.OauthToken);
        oauth.setAccessTokenSecret(token.OauthTokenSecret);
        return token;
    }
}
