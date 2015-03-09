package com.twitter.meil_mitu.twitter4holo.aclog.api.users;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.aclog.IAclogJsonConverter;

public class UsersAPI extends AbsAPI<IAclogJsonConverter>{

    public UsersAPI(AbsOauth oauth, IAclogJsonConverter json){
        super(oauth, json);
    }

    public Stats stats(){
        return new Stats(Oauth, Json);
    }

    public DiscoveredBy discoveredBy(){
        return new DiscoveredBy(Oauth, Json);
    }

    public DiscoveredUsers discoveredUsers(){
        return new DiscoveredUsers(Oauth, Json);
    }

}
