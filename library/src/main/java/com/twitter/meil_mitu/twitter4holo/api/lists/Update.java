package com.twitter.meil_mitu.twitter4holo.api.lists;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.UserList;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Update extends AbsPost {

    public Update(AbsOauth oauth, AbsJsonConverter json,long listId) {
        super(oauth, json);
        addParam("list_id",listId);
    }

    public Update(AbsOauth oauth, AbsJsonConverter json,String slug) {
        super(oauth, json);
        addParam("slug",slug);
    }

    public Update name(String name){
        addParam("name",name);
        return this;
    }

    public Update mode(String mode){
        addParam("mode",mode);
        return this;
    }

    public Update description(String description){
        addParam("description",description);
        return this;
    }

    public Update ownerScreenName(String ownerScreenName){
        addParam("owner_screen_name",ownerScreenName);
        return this;
    }

    public Update ownerId(long ownerId){
        addParam("owner_id",ownerId);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/lists/update.json";
    }

    @Override
    public int allowOauthType() {
        return OauthType.Oauth1;
    }

    @Override
    public boolean isAuthorization() {
        return true;
    }

    @Override
    public UserList call() throws Twitter4HoloException {
        return Json.toUserList(Oauth.post(this));
    }
}
