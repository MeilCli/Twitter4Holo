package com.twitter.meil_mitu.twitter4holo.api.search;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsAPI;

public class SearchAPI extends AbsAPI<ITwitterJsonConverter> {

    public SearchAPI(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public Tweets tweets(String q){return new Tweets(Oauth,Json,q);}

}
