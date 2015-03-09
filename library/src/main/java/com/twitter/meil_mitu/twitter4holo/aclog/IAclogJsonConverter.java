package com.twitter.meil_mitu.twitter4holo.aclog;

import com.squareup.okhttp.Response;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogStats;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogStatus;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogUser;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.util.ArrayList;

public interface IAclogJsonConverter{

    public AclogStatus toStatus(Response res) throws Twitter4HoloException;

    public ArrayList<AclogStatus> toStatusList(Response res) throws Twitter4HoloException;

    public AclogStats toStats(Response res) throws Twitter4HoloException;

    public ArrayList<AclogUser> toUserList(Response res) throws Twitter4HoloException;
}
