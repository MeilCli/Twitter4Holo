package com.twitter.meil_mitu.twitter4holo.aclog;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;

public abstract class AbsAclogGet extends AbsGet<IAclogJsonConverter>{

    protected String Host = "http://aclog.koba789.com";

    public AbsAclogGet(AbsOauth oauth, IAclogJsonConverter json){
        super(oauth, json);
    }

    public void setHost(String host){
        this.Host = host;
    }

}
