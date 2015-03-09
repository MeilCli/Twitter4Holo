package com.twitter.meil_mitu.twitter4holo.query;

import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.query.module.StatusQueryModule;
import com.twitter.meil_mitu.twitter4holo.query.module.UserQueryModule;

public class TwitterQueryHelper{

    protected StatusQueryModule Status;
    protected StatusQueryModule RetweetedStatus;
    protected UserQueryModule User;
    protected UserQueryModule RetweetedUser;
    protected QueryManager Manager;

    public TwitterQueryHelper(){
        Status = new StatusQueryModule("Status");
        RetweetedStatus = new StatusQueryModule("RetweetedStatus");
        User = new UserQueryModule("User");
        RetweetedUser = new UserQueryModule("RetweetedUser");
        Manager = new QueryManager();
        Manager.addModule(Status);
        Manager.addModule(RetweetedStatus);
        Manager.addModule(User);
        Manager.addModule(RetweetedUser);
    }

    public QueryManager getManager(){
        return Manager;
    }

    public synchronized boolean query(Query q, Status status){
        try{
            return queryOrException(q, status);
        }catch(Twitter4HoloException e){
            e.printStackTrace();
            return false;
        }
    }

    public synchronized boolean queryOrException(Query q, Status status) throws Twitter4HoloException{
        Status.init(status);
        RetweetedStatus.init(status.RetweetedStatus);
        User.init(status.User);
        RetweetedUser.init(status.RetweetedStatus != null ? status.RetweetedStatus.User : null);
        return Manager.match(q);
    }

}
