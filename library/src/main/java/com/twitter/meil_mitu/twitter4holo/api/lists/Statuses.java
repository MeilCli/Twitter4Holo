package com.twitter.meil_mitu.twitter4holo.api.lists;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Statuses extends AbsGet<ITwitterJsonConverter> {

    public Statuses(AbsOauth oauth, ITwitterJsonConverter json,long listId) {
        super(oauth, json);
        addParam("list_id",listId);
    }

    public Statuses(AbsOauth oauth,ITwitterJsonConverter json,String slug){
        super(oauth,json);
        addParam("slug",slug);
    }

    public Statuses ownerScreenName(String ownerScreenName){
        addParam("owner_screen_name",ownerScreenName);
        return this;
    }

    public Statuses ownerId(long ownerId){
        addParam("owner_id",ownerId);
        return this;
    }

    public Statuses sinceId(long sinceId){
        addParam("since_id",sinceId);
        return this;
    }

    public Statuses maxId(long maxId){
        addParam("max_id",maxId);
        return this;
    }

    public Statuses count(int count){
        addParam("count",count);
        return this;
    }

    public Statuses includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    public Statuses includeRts(boolean includeRts){
        addParam("include_rts",includeRts);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/lists/statuses.json";
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
    public ResponseList<Status> call() throws Twitter4HoloException {
        return Json.toStatusResponseList(Oauth.get(this));
    }
}
