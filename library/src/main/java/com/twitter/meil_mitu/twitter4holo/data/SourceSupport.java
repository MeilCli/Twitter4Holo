package com.twitter.meil_mitu.twitter4holo.data;

public class SourceSupport{

    public final String Url;
    public final String Client;

    public SourceSupport(String source){
        if(source.charAt(0) == '<'){
            {
                int start = source.indexOf('"', 0);
                int end = source.indexOf('"', start + 1);
                if(start != -1 && end != -1){
                    Url = source.substring(start + 1, end);
                }else{
                    Url = null;
                }
            }
            {
                int start = source.indexOf('>', 0);
                int end = source.indexOf('<', start + 1);
                if(start != -1 && end != -1){
                    Client = source.substring(start + 1, end);
                }else{
                    Client = "";
                }
            }
        }else{
            Url = null;
            Client = source;
        }
    }

    @Override
    public String toString(){
        return "SourceSupport{" +
                "Url='" + Url + '\'' +
                ", Client='" + Client + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof SourceSupport)) return false;

        SourceSupport that = (SourceSupport) o;

        if(!Client.equals(that.Client)) return false;
        if(Url != null ? !Url.equals(that.Url) : that.Url != null) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = Url != null ? Url.hashCode() : 0;
        result = 31 * result + Client.hashCode();
        return result;
    }
}
