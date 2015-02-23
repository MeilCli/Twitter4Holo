package com.twitter.meil_mitu.twitter4holo.api.blocks;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

public class BlocksAPI extends AbsAPI {

    public BlocksAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public List list(){return new List(Oauth,Json);}

    public Ids ids(){return new Ids(Oauth,Json);}

    public Create create(){return new Create(Oauth,Json);}

    public Destroy destroy(){return new Destroy(Oauth,Json);}

}
