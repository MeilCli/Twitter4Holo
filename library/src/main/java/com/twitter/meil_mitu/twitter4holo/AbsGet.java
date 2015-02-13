package com.twitter.meil_mitu.twitter4holo;

public abstract class AbsGet extends AbsMethod{

    protected AbsOauth Oauth;
    protected AbsJsonConverter Json;

    public AbsGet(AbsOauth oauth,AbsJsonConverter json){
        this.Oauth = oauth;
        this.Json = json;
    }

    @Override
    public String method() {
        return "GET";
    }
}
