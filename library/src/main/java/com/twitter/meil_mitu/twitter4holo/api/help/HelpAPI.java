package com.twitter.meil_mitu.twitter4holo.api.help;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsAPI;

public class HelpAPI extends AbsAPI<ITwitterJsonConverter> {

    public HelpAPI(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public Configuration configuration(){return new Configuration(Oauth,Json);}

    public Languages languages(){return new Languages(Oauth,Json);}

    public Privacy privacy(){return new Privacy(Oauth,Json);}

    public Tos tos(){return new Tos(Oauth,Json);}

}
