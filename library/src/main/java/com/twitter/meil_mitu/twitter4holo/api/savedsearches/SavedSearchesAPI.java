package com.twitter.meil_mitu.twitter4holo.api.savedsearches;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

public class SavedSearchesAPI extends AbsAPI {

    public SavedSearchesAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public List list(){return new List(Oauth,Json);}

    public Show show(long id){return new Show(Oauth,Json,id);}

    public Create create(String query){return new Create(Oauth,Json,query);}

    public Destroy destroy(long id){return new Destroy(Oauth,Json,id);}

}
