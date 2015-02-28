package com.twitter.meil_mitu.twitter4holo.api.account;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.io.File;

public class UpdateProfileBanner extends AbsPost<ITwitterJsonConverter> {

    public UpdateProfileBanner(AbsOauth oauth, ITwitterJsonConverter json,File banner) {
        super(oauth, json);
        addFileParam("banner",banner);
    }

    public UpdateProfileBanner width(int width){
        addParam("width",width);
        return this;
    }

    public UpdateProfileBanner height(int height){
        addParam("height",height);
        return this;
    }

    public UpdateProfileBanner offsetLeft(int offsetLeft){
        addParam("offset_left",offsetLeft);
        return this;
    }

    public UpdateProfileBanner offsetTop(int offsetTop){
        addParam("offset_top",offsetTop);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/account/update_profile_banner.json";
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
