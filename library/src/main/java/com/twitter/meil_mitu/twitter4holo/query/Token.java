package com.twitter.meil_mitu.twitter4holo.query;

public class Token{

    public final String Text;
    public final int Type;
    public final IQueryModule Module;

    public Token(String text, int type){
        this(text, type, null);
    }

    public Token(String text, int type, IQueryModule module){
        this.Text = text;
        this.Type = type;
        this.Module = module;
    }
}
