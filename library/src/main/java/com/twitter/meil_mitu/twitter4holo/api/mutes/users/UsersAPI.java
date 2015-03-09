package com.twitter.meil_mitu.twitter4holo.api.mutes.users;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;

public class UsersAPI extends AbsAPI<ITwitterJsonConverter>{

    public UsersAPI(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public Create create(){
        return new Create(Oauth, Json);
    }

    public Destroy destroy(){
        return new Destroy(Oauth, Json);
    }

    public Ids ids(){
        return new Ids(Oauth, Json);
    }

    public List list(){
        return new List(Oauth, Json);
    }

}
