package com.twitter.meil_mitu.twitter4holo.streaming;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.streaming.filter.FilterStream;
import com.twitter.meil_mitu.twitter4holo.streaming.sample.SampleStream;
import com.twitter.meil_mitu.twitter4holo.streaming.user.UserStream;

public class StreamAPI extends AbsAPI<ITwitterJsonConverter>{

    public StreamAPI(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public SampleStream sample(){
        return new SampleStream(Oauth, Json);
    }

    public FilterStream filter(){
        return new FilterStream(Oauth, Json);
    }

    public UserStream user(){
        return new UserStream(Oauth, Json);
    }

}
