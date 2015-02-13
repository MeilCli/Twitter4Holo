package com.twitter.meil_mitu.twitter4holo.api.oauth2;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

public class Oauth2API extends AbsAPI {

    public Oauth2API(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Token token(){
        return new Token(Oauth,Json);
    }

    public InvalidateToken invalidateToken(){
        return new InvalidateToken(Oauth,Json);
    }
}
