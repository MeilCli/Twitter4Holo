package com.twitter.meil_mitu.twitter4holo.query;

public interface IQueryModule<T>{

    public String getParentName();

    public void init(T object);

    public boolean isChild(String child);

    public Class getChildClass(String child);

    public String getChildString(String child);

    public Long getChildLong(String child);

    public Double getChildDouble(String child);

    public Boolean getChildBool(String child);

}
