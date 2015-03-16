package com.twitter.meil_mitu.twitter4holo.item;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.twitter.meil_mitu.twitter4holo.activeandroid.IModelItem;
import com.twitter.meil_mitu.twitter4holo.data.DirectMessage;
import com.twitter.meil_mitu.twitter4holo.data.Entities;

import java.util.Date;

@Table(name = "DirectMessageItem")
public class DirectMessageItem extends Model implements IModelItem{

    @Column(name = "CreatedAt")
    public Date CreatedAt;
    @Column(name = "Entities")
    public Entities Entities;
    @Column(name = "Id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE, index = true)
    public long Id;
    @Column(name = "Recipient")
    public UserItem Recipient;
    @Column(name = "RecipientId",index = true)
    public long RecipientId;
    @Column(name = "Sender")
    public UserItem Sender;
    @Column(name = "SenderId",index = true)
    public long SenderId;
    @Column(name = "Text")
    public String Text;
    @Column(name = "UpdatedAt")
    public long UpdatedAt;

    public DirectMessageItem(){
    }

    public DirectMessageItem(DirectMessage dm){
        CreatedAt = dm.CreatedAt;
        Entities = dm.Entities;
        Id = dm.Id;
        Recipient = new UserItem(dm.Recipient);
        RecipientId=dm.Recipient.Id;
        Sender = new UserItem(dm.Sender);
        SenderId=dm.Sender.Id;
        Text = dm.Text;
        UpdatedAt = System.currentTimeMillis();
    }

    @Override
    public void saveItem(){
        Recipient.saveItem();
        Sender.saveItem();
        save();
    }
}
