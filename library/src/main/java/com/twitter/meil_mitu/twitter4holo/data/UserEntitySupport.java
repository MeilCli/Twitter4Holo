package com.twitter.meil_mitu.twitter4holo.data;

public class UserEntitySupport{

    public final EntitySupport Url, Description;

    public UserEntitySupport(String url, String description, UserEntities entities){
        Url = new EntitySupport(url, entities.Url);
        Description = new EntitySupport(description, entities.Description);
    }

    @Override
    public String toString(){
        return "UserEntitySupport{" +
                "Url=" + Url +
                ", Description=" + Description +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof UserEntitySupport)) return false;

        UserEntitySupport that = (UserEntitySupport) o;

        if(!Description.equals(that.Description)) return false;
        if(!Url.equals(that.Url)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = Url.hashCode();
        result = 31 * result + Description.hashCode();
        return result;
    }
}
