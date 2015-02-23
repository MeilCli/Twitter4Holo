package com.twitter.meil_mitu.twitter4holo.api.account;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.io.File;

public class UpdateProfileImage extends AbsPost {

    public UpdateProfileImage(AbsOauth oauth, AbsJsonConverter json,File image) {
        super(oauth, json);
        addFileParam("image",image);
    }

    public UpdateProfileImage includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    public UpdateProfileImage skipStatus(boolean skipStatus){
        addParam("skip_status",skipStatus);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/account/update_profile_image.json";
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
