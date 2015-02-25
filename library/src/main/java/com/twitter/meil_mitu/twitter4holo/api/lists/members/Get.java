package com.twitter.meil_mitu.twitter4holo.api.lists.members;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.CursorUsers;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Get extends AbsGet {

    public Get(AbsOauth oauth, AbsJsonConverter json,long listId) {
        super(oauth, json);
        addParam("list_id",listId);
    }

    public Get(AbsOauth oauth, AbsJsonConverter json,String slug) {
        super(oauth, json);
        addParam("slug",slug);
    }

    public Get ownerScreenName(String ownerScreenName){
        addParam("owner_screen_name",ownerScreenName);
        return this;
    }

    public Get ownerId(long ownerId){
        addParam("owner_id",ownerId);
        return this;
    }

    public Get count(int count){
        addParam("count",count);
        return this;
    }

    public Get cursor(long cursor){
        addParam("cursor",cursor);
        return this;
    }

    public Get includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    public Get skipStatus(boolean skipStatus){
        addParam("skip_status",skipStatus);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/lists/members.json";
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
    public ResponseData<CursorUsers> call() throws Twitter4HoloException {
        return Json.toCursorUsersResponseData(Oauth.get(this));
    }
}
