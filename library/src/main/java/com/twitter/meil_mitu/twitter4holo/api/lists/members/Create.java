package com.twitter.meil_mitu.twitter4holo.api.lists.members;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.UserList;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Create extends AbsPost {

    public Create(AbsOauth oauth, AbsJsonConverter json,long listId,long userId) {
        super(oauth, json);
        addParam("list_id",listId);
        addParam("user_id",userId);
    }

    public Create(AbsOauth oauth, AbsJsonConverter json,long listId,String screenName) {
        super(oauth, json);
        addParam("list_id",listId);
        addParam("screen_name",screenName);
    }

    public Create(AbsOauth oauth, AbsJsonConverter json,String slug,long userId) {
        super(oauth, json);
        addParam("slug",slug);
        addParam("user_id",userId);
    }

    public Create(AbsOauth oauth, AbsJsonConverter json,String slug,String screenName) {
        super(oauth, json);
        addParam("slug",slug);
        addParam("screen_name",screenName);
    }

    public Create ownerScreenName(String ownerScreenName){
        addParam("owner_screen_name",ownerScreenName);
        return this;
    }

    public Create ownerId(long ownerId){
        addParam("owner_id",ownerId);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/lists/members/create.json";
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
