package com.twitter.meil_mitu.twitter4holo.api.help;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

public class HelpAPI extends AbsAPI {

    public HelpAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Configuration configuration(){return new Configuration(Oauth,Json);}

    public Languages languages(){return new Languages(Oauth,Json);}

    public Privacy privacy(){return new Privacy(Oauth,Json);}

    public Tos tos(){return new Tos(Oauth,Json);}

}
