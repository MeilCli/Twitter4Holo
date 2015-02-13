package com.twitter.meil_mitu.twitter4holo.api.search;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

public class SearchAPI extends AbsAPI {

    public SearchAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Tweets tweets(String q){return new Tweets(Oauth,Json,q);}

}
