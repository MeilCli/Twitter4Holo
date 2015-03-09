package com.twitter.meil_mitu.twitter4holo.api.lists;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.UserList;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Show extends AbsGet<ITwitterJsonConverter>{

    public Show(AbsOauth oauth, ITwitterJsonConverter json, long listId){
        super(oauth, json);
        addParam("list_id", listId);
    }

    public Show(AbsOauth oauth, ITwitterJsonConverter json, String slug){
        super(oauth, json);
        addParam("slug", slug);
    }

    public Show ownerScreenName(String ownerScreenName){
        addParam("owner_screen_name", ownerScreenName);
        return this;
    }

    public Show ownerId(long ownerId){
        addParam("owner_id", ownerId);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/lists/show.json";
    }

    @Override
    public int allowOauthType(){
        return OauthType.Oauth1 | OauthType.Oauth2;
    }

    @Override
    public boolean isAuthorization(){
        return true;
    }

    @Override
    public ResponseData<UserList> call() throws Twitter4HoloException{
        return Json.toUserListResponseData(Oauth.get(this));
    }
}
