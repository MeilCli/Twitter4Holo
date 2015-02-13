package com.twitter.meil_mitu.twitter4holo;

import com.twitter.meil_mitu.twitter4holo.api.directmessages.DirectMessagesAPI;
import com.twitter.meil_mitu.twitter4holo.api.media.MediaAPI;
import com.twitter.meil_mitu.twitter4holo.api.oauth.OauthAPI;
import com.twitter.meil_mitu.twitter4holo.api.oauth2.Oauth2API;
import com.twitter.meil_mitu.twitter4holo.api.search.SearchAPI;
import com.twitter.meil_mitu.twitter4holo.api.statuses.StatusesAPI;

public class Twitter {
    protected AbsOauth Oauth;
    protected AbsJsonConverter Json;
    protected OauthAPI Oauth1;
    protected Oauth2API Oauth2;
    protected StatusesAPI Statuses;
    protected MediaAPI Media;
    protected SearchAPI Search;
    protected DirectMessagesAPI DirectMessage;

    public Twitter(AbsOauth oauth){
        this(oauth,new JsonConverter());
    }

    public Twitter(AbsOauth oauth,AbsJsonConverter json){
        this.Oauth=oauth;
        this.Json=json;
        //API
        this.Oauth1=new OauthAPI(Oauth,Json);
        this.Oauth2 = new Oauth2API(Oauth,Json);
        this.Statuses=new StatusesAPI(Oauth,Json);
        this.Media = new MediaAPI(Oauth,Json);
        this.Search = new SearchAPI(Oauth,Json);
        this.DirectMessage=new DirectMessagesAPI(Oauth,Json);
    }

    public OauthAPI oauth(){return Oauth1;}

    public Oauth2API oauth2(){return Oauth2;}

    public StatusesAPI statuses(){return Statuses;}

    public MediaAPI media(){return Media;}

    public SearchAPI search(){return Search;}

    public DirectMessagesAPI directMessages(){return DirectMessage;}

}
