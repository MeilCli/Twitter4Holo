package com.twitter.meil_mitu.twitter4holo.api.friendships;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.Relationship;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Update extends AbsPost<ITwitterJsonConverter> {

    public Update(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public Update screenName(String screenName){
        addParam("screen_name",screenName);
        return this;
    }

    public Update userId(long userId){
        addParam("user_id",userId);
        return this;
    }

    public Update device(boolean device){
        addParam("device",device);
        return this;
    }

    public Update retweets(boolean retweets){
        addParam("retweets",retweets);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/friendships/update.json";
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
    public Relationship call() throws Twitter4HoloException {
        return Json.toRelationship(Oauth.post(this));
    }
}
