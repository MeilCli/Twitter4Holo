package com.twitter.meil_mitu.twitter4holo.api.application;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

public class ApplicationAPI extends AbsAPI {

    public ApplicationAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public RateLimitStatus rateLimitStatus(){return new RateLimitStatus(Oauth,Json);}

}
