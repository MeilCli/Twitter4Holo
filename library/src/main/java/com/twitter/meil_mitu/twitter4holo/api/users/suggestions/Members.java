package com.twitter.meil_mitu.twitter4holo.api.users.suggestions;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Members extends AbsGet <ITwitterJsonConverter>{

    private String slug;

    public Members(AbsOauth oauth, ITwitterJsonConverter json,String slug) {
        super(oauth, json);
        this.slug=slug;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/users/suggestions/"+slug+"/members.json";
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
    public ResponseList<User> call() throws Twitter4HoloException {
        return Json.toUserResponseList(Oauth.get(this));
    }
}
