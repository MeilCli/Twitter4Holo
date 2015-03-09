package com.twitter.meil_mitu.twitter4holo.api.oauth2;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;

public class Oauth2API extends AbsAPI<ITwitterJsonConverter>{

    public Oauth2API(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public Token token(){
        return new Token(Oauth, Json);
    }

    public InvalidateToken invalidateToken(){
        return new InvalidateToken(Oauth, Json);
    }
}
