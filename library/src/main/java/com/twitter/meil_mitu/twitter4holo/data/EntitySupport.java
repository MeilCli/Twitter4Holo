package com.twitter.meil_mitu.twitter4holo.data;

import android.text.SpannableStringBuilder;

import com.twitter.meil_mitu.twitter4holo.util.EntityUtils;

public class EntitySupport {
    public final SpannableStringBuilder SpannableText;
    public final String PlainText;

    public EntitySupport(String text,Entities entities){
        if(entities!=null){
            SpannableText = EntityUtils.toLinkHtml(text,entities);
            PlainText=EntityUtils.toLinkURL(text,entities);
        }else{
            SpannableText=new SpannableStringBuilder(text);
            PlainText=text;
        }
    }

    @Override
    public String toString() {
        return "EntitySupport{" +
                "SpannableText=" + SpannableText +
                ", PlainText='" + PlainText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntitySupport)) return false;

        EntitySupport that = (EntitySupport) o;

        if (!PlainText.equals(that.PlainText)) return false;
        if (!SpannableText.equals(that.SpannableText)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = SpannableText.hashCode();
        result = 31 * result + PlainText.hashCode();
        return result;
    }
}
