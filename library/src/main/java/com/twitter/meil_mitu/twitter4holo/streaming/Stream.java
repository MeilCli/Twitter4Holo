package com.twitter.meil_mitu.twitter4holo.streaming;

import com.twitter.meil_mitu.twitter4holo.exception.IncorrectException;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Stream extends Thread{

    private InputStream is;
    private BufferedReader br;
    private IStreamParam param;
    private boolean isClose;
    private boolean isConnect;
    private long sleepTime;
    private int retryCount;
    private static final int retryLimit = 5;

    public Stream(IStreamParam param){
        super();
        this.param = param;
        this.isClose = false;
        super.start();
    }


    @Override
    public void run(){
        String line;
        while(isClose == false){
            try{
                this.is = param.getInputStream();
                this.br = new BufferedReader(new InputStreamReader(is));
                if(param.getStreamListener() != null){
                    param.getStreamListener().onConnect();
                }
                isConnect = true;
                retryCount = 0;
                sleepTime = 0;
                while(isClose == false){
                    line = br.readLine();
                    if(line != null && line.length() > 0){
                        try{
                            param.onLine(line);
                        }catch(Exception e){
                        }
                    }
                }
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(param.getStreamListener() != null){
                    param.getStreamListener().onException(e);
                }
                if(e.getHttpStatusCode() == HttpURLConnection.HTTP_FORBIDDEN){
                    isClose = true;
                }
                sleepTime = 10 * 1000;
            }catch(IOException e){
                e.printStackTrace();
                if(param.getStreamListener() != null){
                    param.getStreamListener().onException(e);
                }
                sleepTime = 250;
            }
            if(retryCount >= retryLimit){
                isClose = true;
            }
            if(isClose==true){
                break;
            }
            if(sleepTime == 0){
                sleepTime = 5 * 1000;
            }
            if(is != null){
                try{
                    if(br != null){
                        br.close();
                    }
                    is.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(param.getStreamListener() != null){
                param.getStreamListener().onDisConnect();
            }
            isConnect = false;
            retryCount++;
            try{
                Thread.sleep(sleepTime);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }


        if(is != null){
            try{
                if(br != null){
                    br.close();
                }
                is.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(param.getStreamListener() != null){
            param.getStreamListener().onDisConnect();
            param.getStreamListener().onClose();
        }
        isConnect = false;

    }

    public void close(){
        this.isClose = true;
    }

    public boolean isConnect(){
        return isConnect;
    }

    public boolean isClose(){
        return isClose;
    }

    @Override
    public synchronized void start(){
        throw new IncorrectException("Stream not allow start by user");
    }
}
