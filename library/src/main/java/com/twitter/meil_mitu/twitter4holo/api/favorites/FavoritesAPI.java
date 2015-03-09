package com.twitter.meil_mitu.twitter4holo.api.favorites;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;

public class FavoritesAPI extends AbsAPI<ITwitterJsonConverter>{

    public FavoritesAPI(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public List list(){
        return new List(Oauth, Json);
    }

    public Destroy destroy(long id){
        return new Destroy(Oauth, Json, id);
    }

    public Create create(long id){
        return new Create(Oauth, Json, id);
    }

}
