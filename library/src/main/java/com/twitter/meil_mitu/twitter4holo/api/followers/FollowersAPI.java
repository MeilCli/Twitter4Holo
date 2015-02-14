package com.twitter.meil_mitu.twitter4holo.api.followers;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

public class FollowersAPI extends AbsAPI {

    public FollowersAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Ids ids(){return new Ids(Oauth,Json);}

    public List list(){return new List(Oauth,Json);}
}
