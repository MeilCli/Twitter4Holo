package com.twitter.meil_mitu.twitter4holo.query;

import com.twitter.meil_mitu.twitter4holo.ApplicationTest;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.Twitter;
import com.twitter.meil_mitu.twitter4holo.data.SearchResult;
import com.twitter.meil_mitu.twitter4holo.data.Status;

import junit.framework.TestCase;

public class TwitterQueryHelperTest extends TestCase{

    public void testQueryOrException() throws Exception{
        Twitter tw = ApplicationTest.Twitter1;
        ResponseData<SearchResult> result=tw.search().tweets("(a min_retweets:5) OR (b min_faves:3)").count(13).call();
        TwitterQueryHelper helper=new TwitterQueryHelper();
        int count=0;
        Query q=helper.getManager().parse("(Status.Text indexOf \"a\" && Status.RetweetCount >= 5) || (Status.Text indexOf \"b\" && Status.FavoriteCount >= 3)");
        for(Status s:result.Response.Statuses){
            if(helper.queryOrException(q,s)){
                count++;
            }
        }
        assertEquals(count,13);
    }

}