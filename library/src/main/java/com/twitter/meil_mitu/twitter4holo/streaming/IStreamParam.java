package com.twitter.meil_mitu.twitter4holo.streaming;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.io.InputStream;

public interface IStreamParam{

    public void onLine(String line);

    public InputStream getInputStream() throws Twitter4HoloException;

    public IStreamListener getStreamListener();

}
