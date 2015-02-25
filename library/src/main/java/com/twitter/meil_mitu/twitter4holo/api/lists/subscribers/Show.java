package com.twitter.meil_mitu.twitter4holo.api.lists.subscribers;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Show extends AbsGet {

    public Show(AbsOauth oauth, AbsJsonConverter json,long listId,long userId) {
        super(oauth, json);
        addParam("list_id",listId);
        addParam("user_id",userId);
    }

    public Show(AbsOauth oauth, AbsJsonConverter json,long listId,String screenName) {
        super(oauth, json);
        addParam("list_id",listId);
        addParam("screen_name",screenName);
    }

    public Show(AbsOauth oauth, AbsJsonConverter json,String slug,long userId) {
        super(oauth, json);
        addParam("slug",slug);
        addParam("user_id",userId);
    }

    public Show(AbsOauth oauth, AbsJsonConverter json,String slug,String screenName) {
        super(oauth, json);
        addParam("slug",slug);
        addParam("screen_name",screenName);
    }

    public Show ownerScreenName(String ownerScreenName){
        addParam("owner_screen_name",ownerScreenName);
        return this;
    }

    public Show ownerId(long ownerId){
        addParam("owner_id",ownerId);
        return this;
    }

    public Show includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    public Show skipStatus(boolean skipStatus){
        addParam("skip_status",skipStatus);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/lists/subscribers/show.json";
    }

    @Override
    public int allowOauthType() {
        return OauthType.Oauth1|OauthType.Oauth2;
    }

    @Override
    public boolean isAuthorization() {
        return true;
    }

    @Override
    public ResponseData<User> call() throws Twitter4HoloException {
        return Json.toUserResponseData(Oauth.get(this));
    }
}
