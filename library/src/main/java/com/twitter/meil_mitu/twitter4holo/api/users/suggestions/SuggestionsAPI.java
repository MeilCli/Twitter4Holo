package com.twitter.meil_mitu.twitter4holo.api.users.suggestions;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

public class SuggestionsAPI extends AbsAPI {

    public SuggestionsAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Get get(String slug){return new Get(Oauth,Json,slug);}

    public List list(){return new List(Oauth,Json);}

    public Members members(String slug){return new Members(Oauth,Json,slug);}
}
