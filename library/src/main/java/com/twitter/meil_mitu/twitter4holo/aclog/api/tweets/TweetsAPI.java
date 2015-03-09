package com.twitter.meil_mitu.twitter4holo.aclog.api.tweets;

import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.aclog.IAclogJsonConverter;

public class TweetsAPI extends AbsAPI<IAclogJsonConverter>{

    public TweetsAPI(AbsOauth oauth, IAclogJsonConverter json){
        super(oauth, json);
    }

    public Show show(long id){
        return new Show(Oauth, Json, id);
    }

    public Lookup lookup(long[] ids){
        return new Lookup(Oauth, Json, ids);
    }

    public UserBest userBest(){
        return new UserBest(Oauth, Json);
    }

    public UserTimeline userTimeline(){
        return new UserTimeline(Oauth, Json);
    }

    public UserFavorites userFavorites(){
        return new UserFavorites(Oauth, Json);
    }

    public UserFavoritedBy userFavoritedBy(){
        return new UserFavoritedBy(Oauth, Json);
    }

}
