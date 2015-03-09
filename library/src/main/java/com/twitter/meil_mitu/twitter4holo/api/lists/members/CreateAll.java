package com.twitter.meil_mitu.twitter4holo.api.lists.members;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.UserList;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.util.Utils;

public class CreateAll extends AbsPost<ITwitterJsonConverter>{

    public CreateAll(AbsOauth oauth, ITwitterJsonConverter json, long listId){
        super(oauth, json);
        addParam("list_id", listId);
    }

    public CreateAll(AbsOauth oauth, ITwitterJsonConverter json, String slug){
        super(oauth, json);
        addParam("slug", slug);
    }

    public CreateAll userId(long[] userId){
        addParam("user_id", Utils.toString(userId));
        return this;
    }

    public CreateAll screenName(String[] screenName){
        addParam("screen_name", Utils.toString(screenName));
        return this;
    }

    public CreateAll ownerScreenName(String ownerScreenName){
        addParam("owner_screen_name", ownerScreenName);
        return this;
    }

    public CreateAll ownerId(long ownerId){
        addParam("owner_id", ownerId);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/lists/members/create_all.json";
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
