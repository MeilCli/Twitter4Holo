package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class UserTimeline extends AbsGet {

    public UserTimeline(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public UserTimeline userId(long userId){
        addParam("user_id",userId);
        return this;
    }

    public UserTimeline screenName(String screenName){
        addParam("screen_name",screenName);
        return this;
    }

    public UserTimeline sinceId(long sinceId){
        addParam("since_id",sinceId);
        return this;
    }

    public UserTimeline count(int count){
        addParam("count",count);
        return this;
    }

    public UserTimeline maxId(long maxId){
        addParam("max_id",maxId);
        return this;
    }

    /**
     * must not use in JsonConverter for User
     */
    public UserTimeline trimUser(boolean trimUser){
        addParam("trim_user",trimUser);
        return this;
    }

    public UserTimeline excludeReplies(boolean excludeReplies){
        addParam("exclude_replies",excludeReplies);
        return this;
    }

    public UserTimeline contributorDetails(boolean contributorDetails){
        addParam("contributor_details",contributorDetails);
        return this;
    }

    public UserTimeline includeRts(boolean includeRts){
        addParam("include_rts",includeRts);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/statuses/user_timeline.json";
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
    public ResponseList<Status> call() throws Twitter4HoloException {
        return Json.toStatusResponseList(Oauth.get(this));
    }
}
