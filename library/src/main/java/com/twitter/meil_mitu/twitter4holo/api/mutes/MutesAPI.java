package com.twitter.meil_mitu.twitter4holo.api.mutes;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.api.mutes.users.UsersAPI;

public class MutesAPI extends AbsAPI {

    private UsersAPI Users;

    public MutesAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
        Users=new UsersAPI(Oauth,Json);
    }

    public UsersAPI users(){return Users;}

}
