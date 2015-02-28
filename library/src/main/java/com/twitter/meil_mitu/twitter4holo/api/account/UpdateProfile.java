package com.twitter.meil_mitu.twitter4holo.api.account;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class UpdateProfile extends AbsPost<ITwitterJsonConverter> {

    public UpdateProfile(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public UpdateProfile name(String name){
        addParam("name",name);
        return this;
    }

    public UpdateProfile url(String url){
        addParam("url",url);
        return this;
    }

    public UpdateProfile location(String location){
        addParam("location",location);
        return this;
    }

    public UpdateProfile description(String description){
        addParam("description",description);
        return this;
    }

    public UpdateProfile profileLinkColor(String profileLinkColor){
        addParam("profile_link_color",profileLinkColor);
        return this;
    }

    public UpdateProfile includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    public UpdateProfile skipStatus(boolean skipStatus){
        addParam("skip_status",skipStatus);
        return this;
    }


    @Override
    public String url() {
        return "https://api.twitter.com/1.1/account/update_profile.json";
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
