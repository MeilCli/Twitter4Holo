package com.twitter.meil_mitu.twitter4holo.api.friendships;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

public class FriendshipsAPI extends AbsAPI {

    public FriendshipsAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public NoRetweets noRetweets(){return new NoRetweets(Oauth,Json);}

    public Incoming incoming(){return new Incoming(Oauth,Json);}

    public Outgoing outgoing(){return new Outgoing(Oauth,Json);}

    public Create create(){return new Create(Oauth,Json);}

    public Destroy destroy(){return new Destroy(Oauth,Json);}

    public Update update(){return new Update(Oauth,Json);}

    public Show show(){return new Show(Oauth,Json);}

    public Lookup lookup(){return new Lookup(Oauth,Json);}

}
