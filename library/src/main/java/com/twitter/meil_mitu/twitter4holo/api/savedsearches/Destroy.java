package com.twitter.meil_mitu.twitter4holo.api.savedsearches;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.SavedSearch;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Destroy extends AbsPost {

    private long id;

    public Destroy(AbsOauth oauth, AbsJsonConverter json,long id) {
        super(oauth, json);
        this.id=id;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/saved_searches/destroy/"+id+".json";
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
    public SavedSearch call() throws Twitter4HoloException {
        return Json.toSavedSearch(Oauth.post(this));
    }
}
