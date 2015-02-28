package com.twitter.meil_mitu.twitter4holo.api.savedsearches;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.SavedSearch;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class List extends AbsGet<ITwitterJsonConverter> {

    public List(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/saved_searches/list.json";
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
    public ResponseList<SavedSearch> call() throws Twitter4HoloException {
        return Json.toSavedSearchResponseList(Oauth.get(this));
    }
}
