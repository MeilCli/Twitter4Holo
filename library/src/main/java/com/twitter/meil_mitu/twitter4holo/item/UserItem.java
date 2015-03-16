package com.twitter.meil_mitu.twitter4holo.item;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.twitter.meil_mitu.twitter4holo.activeandroid.IModelItem;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.data.UserEntities;

import java.util.Date;

@Table(name = "UserItem")
public class UserItem extends Model implements IModelItem{

    @Column(name = "CreatedAt")
    public Date CreatedAt;
    @Column(name = "Description")
    public String Description;
    @Column(name = "Entities")
    public UserEntities Entities;
    @Column(name = "FavouritesCount")
    public int FavouritesCount;
    @Column(name = "FollowersCount")
    public int FollowersCount;
    @Column(name = "FriendsCount")
    public int FriendsCount;
    @Column(name = "ListedCount")
    public int ListedCount;
    @Column(name = "StatusesCount")
    public int StatusesCount;
    @Column(name = "IsFollowRequestSent")
    public boolean IsFollowRequestSent;
    @Column(name = "IsFollowing")
    public boolean IsFollowing;
    @Column(name = "IsProtected")
    public boolean IsProtected;
    @Column(name = "IsMuting")
    public boolean IsMuting;
    @Column(name = "IsVerified")
    public boolean IsVerified;
    @Column(name = "ItemId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE, index = true)
    public long Id;
    @Column(name = "Lang")
    public String Lang;
    @Column(name = "Location")
    public String Location;
    @Column(name = "Name")
    public String Name;
    @Column(name = "ProfileBackgroundImageUrl")
    public String ProfileBackgroundImageUrl;
    @Column(name = "ProfileBannerUrl")
    public String ProfileBannerUrl;
    @Column(name = "ProfileImageUrl")
    public String ProfileImageUrl;
    @Column(name = "ScreenName")
    public String ScreenName;
    @Column(name = "Url")
    public String Url;
    @Column(name = "Status")
    public StatusItem Status;
    @Column(name = "StatusId")
    public long StatusId;
    @Column(name = "UpdatedAt")
    public long UpdatedAt;

    public UserItem(){
        super();
    }

    public UserItem(User user){
        super();
        CreatedAt = user.CreatedAt;
        Description = user.Description;
        Entities = user.Entities;
        FavouritesCount = user.FavouritesCount;
        FollowersCount = user.FollowersCount;
        FriendsCount = user.FriendsCount;
        ListedCount = user.ListedCount;
        StatusesCount = user.StatusesCount;
        IsFollowRequestSent = user.IsFollowRequestSent;
        IsFollowing = user.IsFollowing;
        IsProtected = user.IsProtected;
        IsMuting = user.IsMuting;
        IsVerified = user.IsVerified;
        Id = user.Id;
        Lang = user.Lang;
        Location = user.Location;
        Name = user.Name;
        ProfileBackgroundImageUrl = user.ProfileBackgroundImageUrl;
        ProfileBannerUrl = user.ProfileBannerUrl;
        ProfileImageUrl = user.ProfileImageUrl;
        ScreenName = user.ScreenName;
        Url = user.Url;
        if(user.Status != null){
            Status = new StatusItem(user.Status);
            StatusId = user.Status.Id;
        }else{
            StatusId = -1;
        }
        UpdatedAt = System.currentTimeMillis();
    }

    @Override
    public void saveItem(){
        if(Status != null){
            Status.saveItem();
        }
        save();
    }

}
