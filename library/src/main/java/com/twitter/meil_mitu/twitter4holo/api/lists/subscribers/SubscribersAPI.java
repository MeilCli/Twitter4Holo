package com.twitter.meil_mitu.twitter4holo.api.lists.subscribers;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;


public class SubscribersAPI extends AbsAPI<ITwitterJsonConverter>{

    public SubscribersAPI(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public Get get(long listId){
        return new Get(Oauth, Json, listId);
    }

    public Get get(String slug){
        return new Get(Oauth, Json, slug);
    }

    public Create create(long listId){
        return new Create(Oauth, Json, listId);
    }

    public Create create(String slug){
        return new Create(Oauth, Json, slug);
    }

    public Show show(long listId, long userId){
        return new Show(Oauth, Json, listId, userId);
    }

    public Show show(long listId, String screenName){
        return new Show(Oauth, Json, listId, screenName);
    }

    public Show show(String slug, long userId){
        return new Show(Oauth, Json, slug, userId);
    }

    public Show show(String slug, String screenName){
        return new Show(Oauth, Json, slug, slug);
    }

    public Destroy destroy(long listId){
        return new Destroy(Oauth, Json, listId);
    }

    public Destroy destroy(String slug){
        return new Destroy(Oauth, Json, slug);
    }

}
