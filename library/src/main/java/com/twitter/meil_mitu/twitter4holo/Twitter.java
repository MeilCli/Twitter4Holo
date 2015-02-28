package com.twitter.meil_mitu.twitter4holo;

import com.twitter.meil_mitu.twitter4holo.api.account.AccountAPI;
import com.twitter.meil_mitu.twitter4holo.api.application.ApplicationAPI;
import com.twitter.meil_mitu.twitter4holo.api.blocks.BlocksAPI;
import com.twitter.meil_mitu.twitter4holo.api.directmessages.DirectMessagesAPI;
import com.twitter.meil_mitu.twitter4holo.api.favorites.FavoritesAPI;
import com.twitter.meil_mitu.twitter4holo.api.followers.FollowersAPI;
import com.twitter.meil_mitu.twitter4holo.api.friends.FriendsAPI;
import com.twitter.meil_mitu.twitter4holo.api.friendships.FriendshipsAPI;
import com.twitter.meil_mitu.twitter4holo.api.geo.GeoAPI;
import com.twitter.meil_mitu.twitter4holo.api.help.HelpAPI;
import com.twitter.meil_mitu.twitter4holo.api.lists.ListsAPI;
import com.twitter.meil_mitu.twitter4holo.api.media.MediaAPI;
import com.twitter.meil_mitu.twitter4holo.api.mutes.MutesAPI;
import com.twitter.meil_mitu.twitter4holo.api.oauth.OauthAPI;
import com.twitter.meil_mitu.twitter4holo.api.oauth2.Oauth2API;
import com.twitter.meil_mitu.twitter4holo.api.savedsearches.SavedSearchesAPI;
import com.twitter.meil_mitu.twitter4holo.api.search.SearchAPI;
import com.twitter.meil_mitu.twitter4holo.api.statuses.StatusesAPI;
import com.twitter.meil_mitu.twitter4holo.api.trends.TrendsAPI;
import com.twitter.meil_mitu.twitter4holo.api.users.UsersAPI;

public class Twitter {
    protected AbsOauth Oauth;
    protected ITwitterJsonConverter Json;
    protected OauthAPI Oauth1;
    protected Oauth2API Oauth2;
    protected StatusesAPI Statuses;
    protected MediaAPI Media;
    protected SearchAPI Search;
    protected DirectMessagesAPI DirectMessage;
    protected FriendshipsAPI Friendships;
    protected FriendsAPI Friends;
    protected FollowersAPI Followers;
    protected AccountAPI Account;
    protected BlocksAPI Blocks;
    protected UsersAPI Users;
    protected MutesAPI Mutes;
    protected FavoritesAPI Favorites;
    protected ListsAPI Lists;
    protected SavedSearchesAPI SavedSearches;
    protected GeoAPI Geo;
    protected TrendsAPI Trends;
    protected ApplicationAPI Application;
    protected HelpAPI Help;

    public Twitter(AbsOauth oauth){
        this(oauth, TwitterJsonConverter.getDefaultConverter());
    }

    public Twitter(AbsOauth oauth,ITwitterJsonConverter json){
        this.Oauth=oauth;
        this.Json=json;
        //API
        this.Oauth1=new OauthAPI(Oauth,Json);
        this.Oauth2 = new Oauth2API(Oauth,Json);
        this.Statuses=new StatusesAPI(Oauth,Json);
        this.Media = new MediaAPI(Oauth,Json);
        this.Search = new SearchAPI(Oauth,Json);
        this.DirectMessage=new DirectMessagesAPI(Oauth,Json);
        this.Friendships=new FriendshipsAPI(Oauth,Json);
        this.Friends=new FriendsAPI(Oauth,Json);
        this.Followers=new FollowersAPI(Oauth,Json);
        this.Account=new AccountAPI(Oauth,Json);
        this.Blocks=new BlocksAPI(Oauth,Json);
        this.Users=new UsersAPI(Oauth,Json);
        this.Mutes=new MutesAPI(Oauth,Json);
        this.Favorites=new FavoritesAPI(Oauth,Json);
        this.Lists=new ListsAPI(Oauth,Json);
        this.SavedSearches=new SavedSearchesAPI(Oauth,Json);
        this.Geo=new GeoAPI(Oauth,Json);
        this.Trends=new TrendsAPI(Oauth,Json);
        this.Application=new ApplicationAPI(Oauth,Json);
        this.Help=new HelpAPI(Oauth,Json);
    }

    public OauthAPI oauth(){return Oauth1;}

    public Oauth2API oauth2(){return Oauth2;}

    public StatusesAPI statuses(){return Statuses;}

    public MediaAPI media(){return Media;}

    public SearchAPI search(){return Search;}

    public DirectMessagesAPI directMessages(){return DirectMessage;}

    public FriendshipsAPI friendships(){return Friendships;}

    public FriendsAPI friends(){return Friends;}

    public FollowersAPI followers(){return Followers;}

    public AccountAPI account(){return Account;}

    public BlocksAPI blocks(){return Blocks;}

    public UsersAPI users(){return Users;}

    public MutesAPI mutes(){return Mutes;}

    public FavoritesAPI favorites(){return Favorites;}

    public ListsAPI lists(){return Lists;}

    public SavedSearchesAPI savedSearches(){return SavedSearches;}

    public GeoAPI geo(){return Geo;}

    public TrendsAPI trends(){return Trends;}

    public ApplicationAPI application(){return Application;}

    public HelpAPI help(){return Help;}

}
