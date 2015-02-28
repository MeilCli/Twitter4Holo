package com.twitter.meil_mitu.twitter4holo.api.blocks;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsAPI;

public class BlocksAPI extends AbsAPI<ITwitterJsonConverter> {

    public BlocksAPI(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public List list(){return new List(Oauth,Json);}

    public Ids ids(){return new Ids(Oauth,Json);}

    public Create create(){return new Create(Oauth,Json);}

    public Destroy destroy(){return new Destroy(Oauth,Json);}

}
