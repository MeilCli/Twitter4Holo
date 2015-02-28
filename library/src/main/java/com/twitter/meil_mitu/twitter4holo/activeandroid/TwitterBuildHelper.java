package com.twitter.meil_mitu.twitter4holo.activeandroid;

import com.activeandroid.Configuration;
import com.twitter.meil_mitu.twitter4holo.activeandroid.serializer.CurrentUserRetweetSerializer;
import com.twitter.meil_mitu.twitter4holo.activeandroid.serializer.EntitiesSerializer;
import com.twitter.meil_mitu.twitter4holo.activeandroid.serializer.UserEntitiesSerializer;
import com.twitter.meil_mitu.twitter4holo.item.DirectMessageItem;
import com.twitter.meil_mitu.twitter4holo.item.StatusItem;
import com.twitter.meil_mitu.twitter4holo.item.UserItem;

public class TwitterBuildHelper{

    public static void add(Configuration.Builder builder){
        addModels(builder);
        addTypeSerializer(builder);
    }

    private static void addModels(Configuration.Builder builder){
        builder.addModelClass(StatusItem.class);
        builder.addModelClass(UserItem.class);
        builder.addModelClass(DirectMessageItem.class);
        builder.addModelClass(UserItem.class);
    }

    private static void addTypeSerializer(Configuration.Builder builder){
        builder.addTypeSerializer(CurrentUserRetweetSerializer.class);
        builder.addTypeSerializer(EntitiesSerializer.class);
        builder.addTypeSerializer(UserEntitiesSerializer.class);
    }

}
