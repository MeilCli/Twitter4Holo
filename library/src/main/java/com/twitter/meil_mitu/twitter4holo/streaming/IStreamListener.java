package com.twitter.meil_mitu.twitter4holo.streaming;

public interface IStreamListener{

    public void onException(Exception e);

    public void onConnect();

    public void onDisConnect();

    public void onClose();

}
