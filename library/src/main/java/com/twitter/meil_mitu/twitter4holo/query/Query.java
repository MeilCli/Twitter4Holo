package com.twitter.meil_mitu.twitter4holo.query;

import java.util.ArrayList;

public class Query{

    public final int Type;
    public final String String;
    public final Long Long;
    public final Double Double;
    public final Boolean Bool;
    public final ArrayList<Query> Query;
    public final IQueryModule Module;

    public Query(String text, int type){
        this(text, type, null);
    }

    public Query(Long l){
        this.Module = null;
        this.String = null;
        this.Long = l;
        this.Double = null;
        this.Bool = null;
        this.Query = null;
        this.Type = TokenType.Long;
    }

    public Query(Double d){
        this.Module = null;
        this.String = null;
        this.Long = null;
        this.Double = d;
        this.Bool = null;
        this.Query = null;
        this.Type = TokenType.Double;
    }

    public Query(Boolean b){
        this.Module = null;
        this.String = null;
        this.Long = null;
        this.Double = null;
        this.Bool = b;
        this.Query = null;
        this.Type = TokenType.Bool;
    }

    public Query(String text, int type, IQueryModule module){
        this.Module = module;
        this.Type = type;
        if(type == TokenType.String || type == TokenType.Object){
            this.String = text;
        }else{
            this.String = null;
        }
        if(type == TokenType.Long){
            this.Long = java.lang.Long.parseLong(text);
        }else{
            this.Long = null;
        }
        if(type == TokenType.Double){
            this.Double = java.lang.Double.parseDouble(text);
        }else{
            this.Double = null;
        }
        if(type == TokenType.Bool){
            this.Bool = java.lang.Boolean.parseBoolean(text);
        }else{
            this.Bool = null;
        }
        this.Query = null;
    }

    public Query(ArrayList<Query> query){
        this.Module = null;
        this.String = null;
        this.Long = null;
        this.Double = null;
        this.Bool = null;
        this.Query = query;
        boolean isNumberOperator = false;
        int type;
        for(int i = 0; i < query.size(); i++){
            type = query.get(i).Type;
            if(TokenType.isOperator(type)){
                if(TokenType.isNumberOperator(type)){
                    isNumberOperator = true;
                }else{
                    isNumberOperator = false;
                    break;
                }
            }
        }
        if(isNumberOperator == true){
            this.Type = TokenType.QueryNumber;
        }else{
            this.Type = TokenType.QueryBool;
        }
    }

    @Override
    public java.lang.String toString(){
        return "Query{" +
                "Type=" + Type +
                ", String='" + String + '\'' +
                ", Long=" + Long +
                ", Double=" + Double +
                ", Bool=" + Bool +
                ", Query=" + Query +
                ", Module=" + Module +
                '}';
    }

    public String toText(){
        switch(Type){
            case TokenType.Null:
                return "null";
            case TokenType.String:
                return String;
            case TokenType.Long:
                return java.lang.String.valueOf(Long);
            case TokenType.Double:
                return java.lang.String.valueOf(Double);
            case TokenType.Bool:
                return java.lang.String.valueOf(Bool);
            case TokenType.Object:
                return String;
            case TokenType.QueryBool:
            case TokenType.QueryNumber:
                StringBuilder sb = new StringBuilder();
                sb.append('(');
                for(int i = 0; i < Query.size(); i++){
                    if(i > 0){
                        sb.append(' ');
                    }
                    sb.append(Query.get(i).toText());
                }
                sb.append(')');
                return sb.toString();
        }
        return TokenType.operatorToString(Type);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Query)) return false;

        Query query = (Query) o;

        if(Type != query.Type) return false;
        if(Bool != null ? !Bool.equals(query.Bool) : query.Bool != null) return false;
        if(Double != null ? !Double.equals(query.Double) : query.Double != null) return false;
        if(Long != null ? !Long.equals(query.Long) : query.Long != null) return false;
        if(Module != null ? !Module.equals(query.Module) : query.Module != null) return false;
        if(Query != null ? !Query.equals(query.Query) : query.Query != null) return false;
        if(String != null ? !String.equals(query.String) : query.String != null) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = Type;
        result = 31 * result + (String != null ? String.hashCode() : 0);
        result = 31 * result + (Long != null ? Long.hashCode() : 0);
        result = 31 * result + (Double != null ? Double.hashCode() : 0);
        result = 31 * result + (Bool != null ? Bool.hashCode() : 0);
        result = 31 * result + (Query != null ? Query.hashCode() : 0);
        result = 31 * result + (Module != null ? Module.hashCode() : 0);
        return result;
    }
}
