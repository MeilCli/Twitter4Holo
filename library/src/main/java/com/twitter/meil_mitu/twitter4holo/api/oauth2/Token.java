package com.twitter.meil_mitu.twitter4holo.api.oauth2;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.Oauth2Token;
import com.twitter.meil_mitu.twitter4holo.exception.IncorrectException;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.oauth.Oauth2;

public class Token extends AbsPost{
    private Oauth2 oauth2;

    public Token(AbsOauth oauth,AbsJsonConverter json) {
        super(oauth,json);
        if(Oauth instanceof  Oauth2){
            oauth2=(Oauth2)oauth;
        }else{
            throw new IncorrectException("Oauth is not Oauth2");
        }
        this.addParam("grant_type","client_credentials");
    }

    @Override
    public String url() {
        return "https://api.twitter.com/oauth2/token";
    }

    @Override
    public int allowOauthType() {
        return OauthType.Oauth2Basic;
    }

    @Override
    public boolean isAuthorization() {
        return true;
    }

    @Override
    public Oauth2Token call() throws Twitter4HoloException{
        Oauth2Token token = Json.toOauth2Token(Oauth.post(this));
        oauth2.setAccessToken(token.AccessToken);
        oauth2.setTokenType(token.TokenType);
        return token;
    }
}
