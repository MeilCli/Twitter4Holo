package com.twitter.meil_mitu.twitter4holo.aclog;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.aclog.api.tweets.TweetsAPI;
import com.twitter.meil_mitu.twitter4holo.aclog.api.users.UsersAPI;

public class Aclog{

    protected AbsOauth Oauth;
    protected IAclogJsonConverter Json;
    protected TweetsAPI Tweets;
    protected UsersAPI Users;

    public Aclog(AbsOauth oauth){
        this(oauth, AclogJsonConverter.getDefaultConverter());
    }

    public Aclog(AbsOauth oauth, IAclogJsonConverter json){
        this.Oauth = oauth;
        this.Json = json;

        //api
        this.Tweets = new TweetsAPI(Oauth, Json);
        this.Users = new UsersAPI(Oauth, Json);
    }

    public TweetsAPI tweets(){
        return Tweets;
    }

    public UsersAPI users(){
        return Users;
    }

}
