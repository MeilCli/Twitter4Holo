package com.twitter.meil_mitu.twitter4holo.api.application;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;

public class ApplicationAPI extends AbsAPI<ITwitterJsonConverter>{

    public ApplicationAPI(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public RateLimitStatus rateLimitStatus(){
        return new RateLimitStatus(Oauth, Json);
    }

}
