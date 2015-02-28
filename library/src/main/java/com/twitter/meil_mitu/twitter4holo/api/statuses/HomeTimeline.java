package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class HomeTimeline extends AbsGet <ITwitterJsonConverter>{

    public HomeTimeline(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public HomeTimeline count(int count){
        addParam("count",count);
        return this;
    }

    public HomeTimeline sinceId(long sinceId){
        addParam("since_id",sinceId);
        return this;
    }

    public HomeTimeline maxId(long maxId){
        addParam("max_id",maxId);
        return this;
    }

    /**
     * must not use in JsonConverter for User
     */
    public HomeTimeline trimUser(boolean trimUser){
        addParam("trim_user",trimUser);
        return this;
    }

    public HomeTimeline excludeReplies(boolean excludeReplies){
        addParam("exclude_replies",excludeReplies);
        return this;
    }

    public HomeTimeline contributorDetails(boolean contributorDetails){
        addParam("contributor_details",contributorDetails);
        return this;
    }

    public HomeTimeline includeEntities(boolean includeEntities){
        addParam("include_entities",includeEntities);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/statuses/home_timeline.json";
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
    public ResponseList<Status> call() throws Twitter4HoloException {
        return Json.toStatusResponseList(Oauth.get(this));
    }
}
