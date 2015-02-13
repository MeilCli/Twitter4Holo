package com.twitter.meil_mitu.twitter4holo.api.oauth;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.exception.IncorrectException;
import com.twitter.meil_mitu.twitter4holo.oauth.Oauth;

public class OauthAPI extends AbsAPI{

    public OauthAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public String authorize(){
        Oauth oauth;
        if(Oauth instanceof com.twitter.meil_mitu.twitter4holo.oauth.Oauth){
            oauth=(Oauth)Oauth;
        }else{
            throw new IncorrectException("Oauth is not Oauth");
        }
        return "https://api.twitter.com/oauth/authorize?oauth_token="+oauth.getAccessToken();
    }

    public String authenticate(){
        Oauth oauth;
        if(Oauth instanceof com.twitter.meil_mitu.twitter4holo.oauth.Oauth){
            oauth=(Oauth)Oauth;
        }else{
            throw new IncorrectException("Oauth is not Oauth");
        }
        return "https://api.twitter.com/oauth/authenticate?oauth_token="+oauth.getAccessToken();
    }

    public RequestToken requestToken(){return new RequestToken(Oauth,Json);}

    public AccessToken accessToken(String verifier){return new AccessToken(Oauth,Json,verifier);}
}
