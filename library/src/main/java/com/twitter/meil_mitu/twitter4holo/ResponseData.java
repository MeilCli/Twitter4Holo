package com.twitter.meil_mitu.twitter4holo;

import com.twitter.meil_mitu.twitter4holo.data.RateLimit;

public class ResponseData<T>{

    public final T Response;
    public final RateLimit RateLimit;

    public ResponseData(T response, RateLimit rateLimit){
        this.Response = response;
        this.RateLimit = rateLimit;
    }

}
