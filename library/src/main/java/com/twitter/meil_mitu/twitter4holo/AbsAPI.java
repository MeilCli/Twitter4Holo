package com.twitter.meil_mitu.twitter4holo;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;

public abstract class AbsAPI<T> {
    protected AbsOauth Oauth;
    protected T Json;

    public AbsAPI(AbsOauth oauth,T json){
        this.Oauth=oauth;
        this.Json=json;
    }

}
