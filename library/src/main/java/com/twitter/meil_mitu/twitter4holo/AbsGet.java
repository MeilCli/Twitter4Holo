package com.twitter.meil_mitu.twitter4holo;

import com.twitter.meil_mitu.twitter4holo.exception.IncorrectException;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public abstract class AbsGet<T> extends AbsMethod{

    protected AbsOauth Oauth;
    protected T Json;
    protected AbsJsonConverter AbsJson;

    public AbsGet(AbsOauth oauth,T json){
        this.Oauth = oauth;
        if(json instanceof AbsJsonConverter){
            this.Json=json;
            this.AbsJson=(AbsJsonConverter)json;
        }else{
            throw new IncorrectException("json not instanceof AbsJsonConverter");
        }
    }

    @Override
    public String method() {
        return "GET";
    }
}
