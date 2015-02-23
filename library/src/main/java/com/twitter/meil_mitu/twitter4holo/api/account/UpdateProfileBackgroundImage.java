package com.twitter.meil_mitu.twitter4holo.api.account;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.io.File;

public class UpdateProfileBackgroundImage extends AbsPost {

    public UpdateProfileBackgroundImage(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public UpdateProfileBackgroundImage image(File image){
        addFileParam("image",image);
        return this;
    }

    public UpdateProfileBackgroundImage tile(boolean tile){
        addParam("tile",tile);
        return this;
    }

    public UpdateProfileBackgroundImage includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    public UpdateProfileBackgroundImage skipStatus(boolean skipStatus){
        addParam("skip_status",skipStatus);
        return this;
    }

    public UpdateProfileBackgroundImage use(boolean use){
        addParam("use",use);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/account/update_profile_background_image.json";
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
    public User call() throws Twitter4HoloException {
        return Json.toUser(Oauth.post(this));
    }
}
