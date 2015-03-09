package com.twitter.meil_mitu.twitter4holo.api.mutes;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.api.mutes.users.UsersAPI;

public class MutesAPI extends AbsAPI<ITwitterJsonConverter>{

    private UsersAPI Users;

    public MutesAPI(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
        Users = new UsersAPI(Oauth, Json);
    }

    public UsersAPI users(){
        return Users;
    }

}
