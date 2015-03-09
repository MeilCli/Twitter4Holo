package com.twitter.meil_mitu.twitter4holo.api.friends;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;

public class FriendsAPI extends AbsAPI<ITwitterJsonConverter>{

    public FriendsAPI(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public Ids ids(){
        return new Ids(Oauth, Json);
    }

    public List list(){
        return new List(Oauth, Json);
    }
}
