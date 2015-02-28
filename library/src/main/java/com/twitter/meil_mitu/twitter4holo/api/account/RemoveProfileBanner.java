package com.twitter.meil_mitu.twitter4holo.api.account;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class RemoveProfileBanner extends AbsPost<ITwitterJsonConverter> {

    public RemoveProfileBanner(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/account/remove_profile_banner.json";
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
    public Void call() throws Twitter4HoloException {
        // okhttp's connection is called close() in body().string()
        AbsJson.toString(Oauth.post(this).body());
        return null;
    }
}
