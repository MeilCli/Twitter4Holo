package com.twitter.meil_mitu.twitter4holo.item;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.twitter.meil_mitu.twitter4holo.activeandroid.IModelItem;
import com.twitter.meil_mitu.twitter4holo.data.UserList;

import java.util.Date;

@Table(name = "UserListItem", id = "Id")
public class UserListItem extends Model implements IModelItem{

    @Column(name = "Slug")
    public String Slug;
    @Column(name = "Name")
    public String Name;
    @Column(name = "Mode")
    public String Mode;
    @Column(name = "Description")
    public String Description;
    @Column(name = "CreatedAt")
    public Date CreatedAt;
    @Column(name = "SubscriberCount")
    public int SubscriberCount;
    @Column(name = "MemberCount")
    public int MemberCount;
    @Column(name = "Id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE, index = true)
    public long Id;
    @Column(name = "IsFollowing")
    public boolean IsFollowing;
    @Column(name = "User", index = true)
    public UserItem User;
    @Column(name = "UpdatedAt")
    public long UpdatedAt;

    public UserListItem(){
        super();
    }

    public UserListItem(UserList ul){
        super();
        Slug = ul.Slug;
        Name = ul.Name;
        Mode = ul.Mode;
        Description = ul.Description;
        CreatedAt = ul.CreatedAt;
        SubscriberCount = ul.SubscriberCount;
        MemberCount = ul.MemberCount;
        Id = ul.Id;
        IsFollowing = ul.IsFollowing;
        User = new UserItem(ul.User);
        UpdatedAt = System.currentTimeMillis();
    }

    @Override
    public void saveItem(){
        User.saveItem();
        save();
    }
}
