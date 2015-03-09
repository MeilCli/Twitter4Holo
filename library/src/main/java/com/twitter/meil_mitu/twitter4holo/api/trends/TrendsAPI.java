package com.twitter.meil_mitu.twitter4holo.api.trends;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;

public class TrendsAPI extends AbsAPI<ITwitterJsonConverter>{

    public TrendsAPI(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public Place place(int id){
        return new Place(Oauth, Json, id);
    }

    public Available available(){
        return new Available(Oauth, Json);
    }

    public Closest closest(){
        return new Closest(Oauth, Json);
    }

}
