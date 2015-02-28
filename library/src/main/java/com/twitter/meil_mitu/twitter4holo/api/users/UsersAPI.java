package com.twitter.meil_mitu.twitter4holo.api.users;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.api.users.suggestions.SuggestionsAPI;

public class UsersAPI extends AbsAPI<ITwitterJsonConverter> {

    protected SuggestionsAPI Suggestions;

    public UsersAPI(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
        Suggestions=new SuggestionsAPI(Oauth,Json);
    }

    public Lookup lookup(){return new Lookup(Oauth,Json);}

    public Show show(long userId){return new Show(Oauth,Json,userId);}

    public Show show(String screenName){return new Show(Oauth,Json,screenName);}

    public Search search(String q){return new Search(Oauth,Json,q);}

    public ProfileBanner profileBanner(){return new ProfileBanner(Oauth,Json);}

    public SuggestionsAPI suggestions(){return Suggestions;}

    public ReportSpam reportSpam(){return new ReportSpam(Oauth,Json);}

}
