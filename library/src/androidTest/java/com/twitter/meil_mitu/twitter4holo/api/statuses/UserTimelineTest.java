package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.ApplicationTest;
import com.twitter.meil_mitu.twitter4holo.Twitter;
import com.twitter.meil_mitu.twitter4holo.data.Status;

import junit.framework.TestCase;

import java.util.ArrayList;

public class UserTimelineTest extends TestCase{

    public void testCall1() throws Exception{
        Twitter tw1= ApplicationTest.Twitter1;
        ArrayList<Status> list=tw1.statuses().userTimeline().count(13).screenName("meil_mitu").call();
        assertEquals(list.size(),13);
    }

    public void testCall2() throws Exception{
        Twitter tw2= ApplicationTest.Twitter2;
        ArrayList<Status> list=tw2.statuses().userTimeline().count(13).screenName("meil_mitu").call();
        assertEquals(list.size(),13);
    }
}