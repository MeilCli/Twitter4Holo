package com.twitter.meil_mitu.twitter4holo.api.lists.members;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.UserList;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Destroy extends AbsPost {

    public Destroy(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Destroy listId(long listId){
        addParam("list_id",listId);
        return this;
    }

    public Destroy slug(String slug){
        addParam("slug",slug);
        return this;
    }

    public Destroy userId(long userId){
        addParam("user_id",userId);
        return this;
    }

    public Destroy screenName(String screenName){
        addParam("screen_name",screenName);
        return this;
    }

    public Destroy ownerScreenName(String ownerScreenName){
        addParam("owner_screen_name",ownerScreenName);
        return this;
    }

    public Destroy ownerId(long ownerId){
        addParam("owner_id",ownerId);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/lists/members/destroy.json";
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
