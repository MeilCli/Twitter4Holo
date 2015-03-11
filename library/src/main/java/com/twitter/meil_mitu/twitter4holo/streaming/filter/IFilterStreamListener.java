package com.twitter.meil_mitu.twitter4holo.streaming.filter;

import com.twitter.meil_mitu.twitter4holo.data.Status;

public interface IFilterStreamListener{

    public void onUnknown(String line);

    public void onStatus(Status status);

}
