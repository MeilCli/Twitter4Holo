package com.twitter.meil_mitu.twitter4holo;

import com.twitter.meil_mitu.twitter4holo.oauth.Oauth;
import com.twitter.meil_mitu.twitter4holo.oauth.Oauth2;
import com.twitter.meil_mitu.twitter4holo.oauth.OauthEcho;

import java.util.HashMap;

public class TwitterFactory{

    private static TwitterFactory instance;

    public static TwitterFactory getInstance(){
        if(instance == null){
            instance = new TwitterFactory();
        }
        return instance;
    }

    private TwitterFactory(){
    }

    private HashMap<OauthItem, Twitter> map = new HashMap<OauthItem, Twitter>();

    public <T extends AbsOauth> AbsOauth getOauth(Class<T> oauthClass, String consumerKey,
                                                  String consumerSecret){
        return getOauth(oauthClass, consumerKey, consumerSecret, null, null);
    }

    public <T extends AbsOauth> AbsOauth getOauth(Class<T> oauthClass, String consumerKey,
                                                  String consumerSecret, String accessToken, String accessTokenSecret){
        Twitter tw = getTwitter(oauthClass, consumerKey, consumerSecret, accessToken, accessTokenSecret);
        if(tw != null){
            return tw.getOauth();
        }
        return null;
    }

    public <T extends AbsOauth> Twitter getTwitter(Class<T> oauthClass, String consumerKey,
                                                   String consumerSecret){
        return getTwitter(oauthClass, consumerKey, consumerSecret, null, null);
    }

    public <T extends AbsOauth> Twitter getTwitter(Class<T> oauthClass, String consumerKey,
                                                   String consumerSecret, String accessToken, String accessTokenSecret){
        if(Oauth2.class.equals(oauthClass) == false && (accessToken == null || accessTokenSecret == null)){
            throw new IllegalArgumentException("accessToken or accessTokenSecret is null");
        }
        OauthItem item = new OauthItem(oauthClass, consumerKey, consumerSecret, accessToken, accessTokenSecret);
        if(map.containsKey(item)){
            Twitter tw = map.get(item);
            if(tw != null){
                return tw;
            }
        }
        if(Oauth.class.equals(oauthClass)){
            Twitter tw = new Twitter(new Oauth(null, consumerKey, consumerSecret, accessToken, accessTokenSecret));
            map.put(item, tw);
            return tw;
        }else if(Oauth2.class.equals(oauthClass)){
            Twitter tw = new Twitter(new Oauth2(null, consumerKey, consumerSecret));
            map.put(item, tw);
            return tw;
        }else if(OauthEcho.class.equals(oauthClass)){
            Twitter tw = new Twitter(new OauthEcho(null, null, consumerKey, consumerSecret, accessToken, accessTokenSecret));
            map.put(item, tw);
            return tw;
        }
        throw new IllegalArgumentException("oauthClass is not default defined");
    }

    private class OauthItem{

        private Class<? extends AbsOauth> oauthClass;
        private String consumerKey;
        private String consumerSecret;
        private String accessToken;
        private String accessTokenSecret;

        OauthItem(Class<? extends AbsOauth> oauthClass, String consumerKey,
                  String consumerSecret, String accessToken, String accessTokenSecret){
            this.oauthClass = oauthClass;
            this.consumerKey = consumerKey;
            this.consumerSecret = consumerSecret;
            this.accessToken = accessToken;
            this.accessTokenSecret = accessTokenSecret;
        }

        @Override
        public String toString(){
            return "OauthItem{" +
                    "oauthClass=" + oauthClass +
                    ", consumerKey='" + consumerKey + '\'' +
                    ", consumerSecret='" + consumerSecret + '\'' +
                    ", accessToken='" + accessToken + '\'' +
                    ", accessTokenSecret='" + accessTokenSecret + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(!(o instanceof OauthItem)) return false;

            OauthItem oauthItem = (OauthItem) o;

            if(!oauthClass.equals(oauthItem.oauthClass)) return false;
            if(!consumerKey.equals(oauthItem.consumerKey)) return false;
            if(!consumerSecret.equals(oauthItem.consumerSecret)) return false;
            if(accessToken != null ? !accessToken.equals(oauthItem.accessToken) : false)
                return false;
            if(accessTokenSecret != null ? !accessTokenSecret.equals(oauthItem.accessTokenSecret) : false)
                return false;

            return true;
        }

        @Override
        public int hashCode(){
            int result = oauthClass.hashCode();
            result = 31 * result + consumerKey.hashCode();
            result = 31 * result + consumerSecret.hashCode();
            result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
            result = 31 * result + (accessTokenSecret != null ? accessTokenSecret.hashCode() : 0);
            return result;
        }
    }
}
