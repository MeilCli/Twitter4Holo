package com.twitter.meil_mitu.twitter4holo.api.lists;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.CursorLists;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Memberships extends AbsGet {

    public Memberships(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Memberships userId(long userId){
        addParam("user_id",userId);
        return this;
    }

    public Memberships screenName(String screenName){
        addParam("screen_name",screenName);
        return this;
    }

    public Memberships count(int count){
        addParam("count",count);
        return this;
    }

    public Memberships cursor(long cursor){
        addParam("cursor",cursor);
        return this;
    }

    public Memberships filterToOwnedLists(boolean filterToOwnedLists){
        addParam("filter_to_owned_lists",filterToOwnedLists);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/lists/memberships.json";
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
    public ResponseData<CursorLists> call() throws Twitter4HoloException {
        return Json.toCursorListsResponseData(Oauth.get(this));
    }
}
