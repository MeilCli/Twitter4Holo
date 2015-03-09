package com.twitter.meil_mitu.twitter4holo;

public class Twitter4HoloConfig{

    public static boolean IsDebug;

    private String userAgent = "Mozilla/5.0 (Linux; U; Android 4.1.2; jp-jp; SonySOL21 Build/9.1.D.0.395)AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
    ;

    public String getUserAgent(){
        return userAgent;
    }

    public void setUserAgent(String userAgent){
        this.userAgent = userAgent;
    }

    private boolean useHttp2 = true;

    public boolean isUseHttp2(){
        return useHttp2;
    }

    public void setUseHttp2(boolean useHttp2){
        this.useHttp2 = useHttp2;
    }

    private boolean useSpdy = true;

    public boolean isUseSpdy(){
        return useSpdy;
    }

    public void setUseSpdy(boolean useSpdy){
        this.useSpdy = useSpdy;
    }

    private long connectTimeoutSecond = 20;

    public long getConnectTimeoutSecond(){
        return connectTimeoutSecond;
    }

    public void setConnectTimeoutSecond(long connectTimeoutSecond){
        this.connectTimeoutSecond = connectTimeoutSecond;
    }

    private long readTimeoutSecond = 40;

    public long getReadTimeoutSecond(){
        return readTimeoutSecond;
    }

    public void setReadTimeoutSecond(long readTimeoutSecond){
        this.readTimeoutSecond = readTimeoutSecond;
    }
}
