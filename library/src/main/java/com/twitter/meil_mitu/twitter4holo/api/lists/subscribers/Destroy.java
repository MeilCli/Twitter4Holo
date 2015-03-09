package com.twitter.meil_mitu.twitter4holo.api.lists.subscribers;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.UserList;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Destroy extends AbsPost<ITwitterJsonConverter>{

    public Destroy(AbsOauth oauth, ITwitterJsonConverter json, long listId){
        super(oauth, json);
        addParam("list_id", listId);
    }

    public Destroy(AbsOauth oauth, ITwitterJsonConverter json, String slug){
        super(oauth, json);
        addParam("slug", slug);
    }

    public Destroy ownerScreenName(String ownerScreenName){
        addParam("owner_screen_name", ownerScreenName);
        return this;
    }

    public Destroy ownerId(long ownerId){
        addParam("owner_id", ownerId);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/lists/subscribers/destroy.json";
    }

    @Override
    public int allowOauthType(){
        return OauthType.Oauth1;
    }

    @Override
    public boolean isAuthorization(){
        return true;
    }

    @Override
    public UserList call() throws Twitter4HoloException{
        return Json.toUserList(Oauth.post(this));
    }
}
