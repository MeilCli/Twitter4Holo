package com.twitter.meil_mitu.twitter4holo.api.lists.members;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

public class MembersAPI extends AbsAPI {

    public MembersAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Destroy destroy(){return new Destroy(Oauth,Json);}

    public CreateAll createAll(long listId){return new CreateAll(Oauth,Json,listId);}

    public CreateAll createAll(String slug){return new CreateAll(Oauth,Json,slug);}

    public Show show(long listId,long userId){return new Show(Oauth,Json,listId,userId);}

    public Show show(long listId,String screenName){return new Show(Oauth,Json,listId,screenName);}

    public Show show(String slug,long userId){return new Show(Oauth,Json,slug,userId);}

    public Show show(String slug,String screenName){return new Show(Oauth,Json,slug,screenName);}

    public Get get(long listId){return new Get(Oauth,Json,listId);}

    public Get get(String slug){return new Get(Oauth,Json,slug);}

    public Create create(long listId,long userId){return new Create(Oauth,Json,listId,userId);}

    public Create create(long listId,String screenName){return new Create(Oauth,Json,listId,screenName);}

    public Create create(String slug,long userId){return new Create(Oauth,Json,slug,userId);}

    public Create create(String slug,String screenName){return new Create(Oauth,Json,slug,screenName);}

    public DestroyAll destroyAll(long listId){return new DestroyAll(Oauth,Json,listId);}

    public DestroyAll destroyAll(String slug){return new DestroyAll(Oauth,Json,slug);}

}
