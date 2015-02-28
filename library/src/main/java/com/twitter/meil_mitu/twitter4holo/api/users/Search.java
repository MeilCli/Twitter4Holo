package com.twitter.meil_mitu.twitter4holo.api.users;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Search extends AbsGet<ITwitterJsonConverter> {

    public Search(AbsOauth oauth, ITwitterJsonConverter json,String q) {
        super(oauth, json);
        addParam("q",q);
    }

    public Search page(int page){
        addParam("page",page);
        return this;
    }

    public Search count(int count){
        addParam("count",count);
        return this;
    }

    public Search includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/users/search.json";
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
    public ResponseList<User> call() throws Twitter4HoloException {
        return Json.toUserResponseList(Oauth.get(this));
    }
}
