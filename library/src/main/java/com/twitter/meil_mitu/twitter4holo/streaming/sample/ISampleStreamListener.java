package com.twitter.meil_mitu.twitter4holo.streaming.sample;

import com.twitter.meil_mitu.twitter4holo.data.Status;

public interface ISampleStreamListener{

    public void onUnknown(String line);

    public void onStatus(Status status);

}
