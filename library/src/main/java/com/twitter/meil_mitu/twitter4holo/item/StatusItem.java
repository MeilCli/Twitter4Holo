package com.twitter.meil_mitu.twitter4holo.item;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.twitter.meil_mitu.twitter4holo.activeandroid.IModelItem;
import com.twitter.meil_mitu.twitter4holo.data.CurrentUserRetweet;
import com.twitter.meil_mitu.twitter4holo.data.Entities;
import com.twitter.meil_mitu.twitter4holo.data.Status;

import java.util.Date;

@Table(name = "StatusItem", id = "Id")
public class StatusItem extends Model implements IModelItem{

    @Column(name = "CreatedAt")
    public Date CreatedAt;
    @Column(name = "CurrentUserRetweet")
    public CurrentUserRetweet CurrentUserRetweet;
    @Column(name = "Entities")
    public Entities Entities;
    @Column(name = "ExtendedEntities")
    public Entities ExtendedEntities;
    @Column(name = "FavoriteCount")
    public int FavoriteCount;
    @Column(name = "RetweetCount")
    public int RetweetCount;
    @Column(name = "IsFavorited")
    public boolean IsFavorited;
    @Column(name = "IsRetweeted")
    public boolean IsRetweeted;
    @Column(name = "Id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE, index = true)
    public long Id;
    @Column(name = "InReplyToStatusId")
    public long InReplyToStatusId;
    @Column(name = "InReplyToUserId")
    public long InReplyToUserId;
    @Column(name = "InReplyToScreenName")
    public String InReplyToScreenName;
    @Column(name = "Lang")
    public String Lang;
    @Column(name = "Source")
    public String Source;
    @Column(name = "Text")
    public String Text;
    @Column(name = "RetweetedStatus")
    public StatusItem RetweetedStatus;
    @Column(name = "User", index = true)
    public UserItem User;
    @Column(name = "UpdatedAt")
    public long UpdatedAt;

    public StatusItem(){
        super();
    }

    public StatusItem(Status status){
        super();
        CreatedAt = status.CreatedAt;
        CurrentUserRetweet = status.CurrentUserRetweet;
        Entities = status.Entities;
        ExtendedEntities = status.ExtendedEntities;
        FavoriteCount = status.FavoriteCount;
        RetweetCount = status.RetweetCount;
        IsFavorited = status.IsFavorited;
        IsRetweeted = status.IsRetweeted;
        Id = status.Id;
        InReplyToStatusId = status.InReplyToStatusId;
        InReplyToUserId = status.InReplyToUserId;
        InReplyToScreenName = status.InReplyToScreenName;
        Lang = status.Lang;
        Source = status.Source;
        Text = status.Text;
        if(status.RetweetedStatus != null){
            RetweetedStatus = new StatusItem(status.RetweetedStatus);
        }
        if(status.User != null){
            User = new UserItem(status.User);
        }
        UpdatedAt = System.currentTimeMillis();
    }

    @Override
    public void saveItem(){
        if(RetweetedStatus != null){
            RetweetedStatus.saveItem();
        }
        if(User != null){
            User.saveItem();
        }
        save();
    }

}
