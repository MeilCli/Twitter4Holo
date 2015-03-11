package com.twitter.meil_mitu.twitter4holo.streaming.user;

import com.twitter.meil_mitu.twitter4holo.data.DirectMessage;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.data.UserList;

public interface IUserStreamListener{

    public void onUnknown(String line);

    public void onStatus(Status status);

    public void onDirectMessage(DirectMessage directMessage);

    public void onBlock(User user, User blockedUser);

    public void onUnBlock(User user, User unblockedUser);

    public void onFavorite(User user, User favoritedUser, Status status);

    public void onUnFavorite(User user, User unfavoritedUser, Status status);

    public void onFollow(User user, User followedUser);

    public void onUnFollow(User user, User unfollowedUser);

    public void onListCreated(User user, UserList userList);

    public void onListDestroyed(User user, UserList userList);

    public void onListUpdated(User user, UserList userList);

    public void onListMemberAdded(User user, User addedUser, UserList userList);

    public void onListMemberRemoved(User user, User removedUser, UserList userList);

    public void onListUserSubscribed(User user, User ownerUser, UserList userList);

    public void onListUserUnSubscribed(User user, User ownerUser, UserList userList);

    public void onUserUpdate(User user);

    public void onMute(User user, User mutedUser);

    public void onUnMute(User user, User unmutedUser);

}
