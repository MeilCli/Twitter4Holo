package com.twitter.meil_mitu.twitter4holo.streaming.user;

import com.twitter.meil_mitu.twitter4holo.data.DirectMessage;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.data.UserList;

public class UserStreamListener implements IUserStreamListener{

    @Override
    public void onUnknown(String line){

    }

    @Override
    public void onStatus(Status status){

    }

    @Override
    public void onDirectMessage(DirectMessage directMessage){

    }

    @Override
    public void onBlock(User user, User blockedUser){

    }

    @Override
    public void onUnBlock(User user, User unblockedUser){

    }

    @Override
    public void onFavorite(User user, User favoritedUser, Status status){

    }

    @Override
    public void onUnFavorite(User user, User unfavoritedUser, Status status){

    }

    @Override
    public void onFollow(User user, User followedUser){

    }

    @Override
    public void onUnFollow(User user, User unfollowedUser){

    }

    @Override
    public void onListCreated(User user, UserList userList){

    }

    @Override
    public void onListDestroyed(User user, UserList userList){

    }

    @Override
    public void onListUpdated(User user, UserList userList){

    }

    @Override
    public void onListMemberAdded(User user, User addedUser, UserList userList){

    }

    @Override
    public void onListMemberRemoved(User user, User removedUser, UserList userList){

    }

    @Override
    public void onListUserSubscribed(User user, User ownerUser, UserList userList){

    }

    @Override
    public void onListUserUnSubscribed(User user, User ownerUser, UserList userList){

    }

    @Override
    public void onUserUpdate(User user){

    }

    @Override
    public void onMute(User user, User mutedUser){

    }

    @Override
    public void onUnMute(User user, User unmutedUser){

    }
}
