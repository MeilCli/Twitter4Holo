package com.twitter.meil_mitu.twitter4holo.api;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;

public abstract class AbsAPI {
    protected AbsOauth Oauth;
    protected AbsJsonConverter Json;

    public AbsAPI(AbsOauth oauth,AbsJsonConverter json){
        this.Oauth=oauth;
        this.Json=json;
    }

}
