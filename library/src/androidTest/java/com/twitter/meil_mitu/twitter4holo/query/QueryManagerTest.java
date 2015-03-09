package com.twitter.meil_mitu.twitter4holo.query;

import junit.framework.TestCase;

import java.util.ArrayList;

public class QueryManagerTest extends TestCase{

    public void testParse() throws Exception{
        QueryManager manager=new QueryManager();
        String text ="(\"ad \\\" \\\\ \" indexof \"bsh\" ||     1 * 3 > 2.3)or ( true == false ) (2.5 == 2.5) 4 + 2 > 2 or 3 < 3";

        ArrayList<Query> list = new ArrayList<Query>();

        ArrayList<Query> l1 = new ArrayList<Query>();
        {
            Query q1 = new Query("ad \" \\ ", TokenType.String);
            Query q2 = new Query(null, TokenType.IndexOf);
            Query q3 = new Query("bsh", TokenType.String);
            Query q4 = new Query(null, TokenType.Or);
            Query q5 = new Query(1L);
            Query q6 = new Query(null, TokenType.Asterisk);
            Query q7 = new Query(3L);
            Query q8 = new Query(null, TokenType.Greater);
            Query q9 = new Query(2.3);

            l1.add(q1);
            l1.add(q2);
            l1.add(q3);
            l1.add(q4);
            l1.add(q5);
            l1.add(q6);
            l1.add(q7);
            l1.add(q8);
            l1.add(q9);
        }
        list.add(new Query(l1));
        list.add(new Query(null,TokenType.Or));

        ArrayList<Query> l2 = new ArrayList<Query>();
        {
            Query q1 = new Query("true",TokenType.Bool);
            Query q2 = new Query(null,TokenType.Equal);
            Query q3 = new Query("false",TokenType.Bool);

            l2.add(q1);
            l2.add(q2);
            l2.add(q3);
        }
        list.add(new Query(l2));
        list.add(new Query(null,TokenType.And));

        ArrayList<Query> l3 = new ArrayList<Query>();
        {
            Query q1=new Query(2.5);
            Query q2=new Query(null,TokenType.Equal);
            Query q3=new Query(2.5);

            l3.add(q1);
            l3.add(q2);
            l3.add(q3);
        }
        list.add(new Query(l3));
        list.add(new Query(null,TokenType.And));

        list.add(new Query(4L));
        list.add(new Query(null,TokenType.Plus));
        list.add(new Query(2L));
        list.add(new Query(null,TokenType.Greater));
        list.add(new Query(2L));

        list.add(new Query(null,TokenType.Or));
        list.add(new Query(3L));
        list.add(new Query(null,TokenType.Less));
        list.add(new Query(3L));

        Query query = new Query(list);
        assertEquals(manager.parse(text),manager.rawParse(query));
    }

    public void testParse2() throws Exception{
        QueryManager manager=new QueryManager();
        String q1="1 + 2 * 3 + 3 * (1 + 2) < 16 or 1 == 2 (3 * 3.4 != 2)";
        String q2="(((((1 + (2 * 3)) + (3 * (1 + 2))) < 16) || ((1 == 2) && (((3 * 3.4) != 2)))))";
        //throw new Exception(manager.parse(q1).toText());
        assertEquals(manager.parse(q1).toText(),q2);
    }

    public void testMatch()throws Exception{
        QueryManager manager=new QueryManager();
        String t1="\"abcde\" indexOf \"cd\" 1 < 2 (1 < 2 or 1 > (1 + 1) * 2)";
        String t2= "(1 == 1 or 2 == 1) (2 == 1 or 1 == 1) and (1 != 1) == false";
        String t3="(\"qqaqq\" indexOf \"a\" 7 >= 5) || (\"qqbqq\" indexOf \"b\" 5 >= 3)";
        String f1 = "1 > 2 && true == true";
        String f2 = "false";
        assertEquals(manager.match(manager.parse(t1)),true);
        assertEquals(manager.match(manager.parse(t2)),true);
        assertEquals(manager.match(manager.parse(t3)),true);
        assertEquals(manager.match(manager.parse(f1)),false);
        assertEquals(manager.match(manager.parse(f2)),false);
    }
}