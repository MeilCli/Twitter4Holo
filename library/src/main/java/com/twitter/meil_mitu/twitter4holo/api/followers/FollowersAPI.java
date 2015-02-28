package com.twitter.meil_mitu.twitter4holo.api.followers;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsAPI;

public class FollowersAPI extends AbsAPI <ITwitterJsonConverter>{

    public FollowersAPI(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public Ids ids(){return new Ids(Oauth,Json);}

    public List list(){return new List(Oauth,Json);}
}
