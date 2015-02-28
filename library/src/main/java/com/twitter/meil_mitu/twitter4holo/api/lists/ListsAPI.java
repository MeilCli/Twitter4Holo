package com.twitter.meil_mitu.twitter4holo.api.lists;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsAPI;
import com.twitter.meil_mitu.twitter4holo.api.lists.members.MembersAPI;
import com.twitter.meil_mitu.twitter4holo.api.lists.subscribers.SubscribersAPI;

public class ListsAPI extends AbsAPI<ITwitterJsonConverter> {

    protected MembersAPI Members;
    protected SubscribersAPI Subscribers;

    public ListsAPI(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
        this.Members=new MembersAPI(Oauth,Json);
        this.Subscribers=new SubscribersAPI(Oauth,Json);
    }

    public List list(){return new List(Oauth,Json);}

    public Statuses statuses(long listId){return new Statuses(Oauth,Json,listId);}

    public Statuses statuses(String slug){return new Statuses(Oauth,Json,slug);}

    public MembersAPI members(){return Members;}

    public Memberships memberships(){return new Memberships(Oauth,Json);}

    public SubscribersAPI subscribers(){return Subscribers;}

    public Destroy destroy(long listId){return new Destroy(Oauth,Json,listId);}

    public Destroy destroy(String slug){return new Destroy(Oauth,Json,slug);}

    public Update update(long listId){return new Update(Oauth,Json,listId);}

    public Update update(String slug){return new Update(Oauth,Json,slug);}

    public Create create(){return new Create(Oauth,Json);}

    public Show show(long listId){return new Show(Oauth,Json,listId);}

    public Show show(String slug){return new Show(Oauth,Json,slug);}

    public Subscriptions subscriptions(){return new Subscriptions(Oauth,Json);}

    public Ownerships ownerships(){return new Ownerships(Oauth,Json);}

}
