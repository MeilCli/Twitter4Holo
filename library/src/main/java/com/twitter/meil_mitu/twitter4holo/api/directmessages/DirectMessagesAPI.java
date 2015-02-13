package com.twitter.meil_mitu.twitter4holo.api.directmessages;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

public class DirectMessagesAPI extends AbsAPI {

    public DirectMessagesAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Sent sent(){return new Sent(Oauth,Json);}

    public Show show(long id){return new Show(Oauth,Json,id);}

    public Get get(){return new Get(Oauth,Json);}

    public Destroy destroy(long id){return new Destroy(Oauth,Json,id);}

    public PostNew postNew(String text){return new PostNew(Oauth,Json,text);}
}
