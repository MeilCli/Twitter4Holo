package com.twitter.meil_mitu.twitter4holo;

public abstract class AbsAPI<T>{

    protected AbsOauth Oauth;
    protected T Json;

    public AbsAPI(AbsOauth oauth, T json){
        this.Oauth = oauth;
        this.Json = json;
    }

}
