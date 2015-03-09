package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;

public class StatusesAPI extends AbsAPI<ITwitterJsonConverter>{

    public StatusesAPI(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public MentionsTimeline mentionsTimeline(){
        return new MentionsTimeline(Oauth, Json);
    }

    public UserTimeline userTimeline(){
        return new UserTimeline(Oauth, Json);
    }

    public HomeTimeline homeTimeline(){
        return new HomeTimeline(Oauth, Json);
    }

    public RetweetsOfMe retweetsOfMe(){
        return new RetweetsOfMe(Oauth, Json);
    }

    public Retweets retweets(long id){
        return new Retweets(Oauth, Json, id);
    }

    public Show show(long id){
        return new Show(Oauth, Json, id);
    }

    public Destroy destroy(long id){
        return new Destroy(Oauth, Json, id);
    }

    public Update update(String status){
        return new Update(Oauth, Json, status);
    }

    public Retweet retweet(long id){
        return new Retweet(Oauth, Json, id);
    }

    public Oembed oembed(long id){
        return new Oembed(Oauth, Json, id);
    }

    public Retweeters retweeters(long id){
        return new Retweeters(Oauth, Json, id);
    }

    public Lookup lookup(long[] id){
        return new Lookup(Oauth, Json, id);
    }
}
