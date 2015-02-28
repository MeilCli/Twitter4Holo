package com.twitter.meil_mitu.twitter4holo.api.lists;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.UserList;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Create extends AbsPost<ITwitterJsonConverter> {

    public Create(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public Create name(String name){
        addParam("name",name);
        return this;
    }

    public Create mode(String mode){
        addParam("mode",mode);
        return this;
    }

    public Create description(String description){
        addParam("description",description);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/lists/create.json";
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
