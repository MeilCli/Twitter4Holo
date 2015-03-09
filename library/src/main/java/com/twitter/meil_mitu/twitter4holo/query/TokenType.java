package com.twitter.meil_mitu.twitter4holo.query;

public class TokenType{

    //Object
    public static final int Null = 0;
    public static final int String = 1;
    public static final int Long = 2;
    public static final int Double = 3;
    public static final int Bool = 4;
    public static final int Object = 5;

    //operator
    public static final int Plus = 10;// +
    public static final int Minus = 11;// -
    public static final int Asterisk = 12;// *
    public static final int Slash = 13;// /
    public static final int Equal = 14;
    public static final int NotEqual = 15;
    public static final int Greater = 16;// >
    public static final int EqualGreater = 17;// >=
    public static final int Less = 18;// <
    public static final int EqualLess = 19;// <=
    public static final int And = 20;
    public static final int Or = 21;

    public static final int LeftParenthesis = 30;// (
    public static final int RightParenthesis = 31;// )

    //other
    public static final int QueryBool = 40;
    public static final int QueryNumber = 41;

    //custom operator
    public static final int IndexOf = 50;
    public static final int StartsWith = 51;
    public static final int EndsWith = 52;
    public static final int Find = 53;

    public static boolean isObject(int type){
        switch(type){
            case TokenType.Null:
            case TokenType.String:
            case TokenType.Long:
            case TokenType.Double:
            case TokenType.Bool:
            case TokenType.Object:
            case TokenType.QueryBool:
            case TokenType.QueryNumber:
                return true;
            default:
                return false;
        }
    }

    public static boolean isOperator(int type){
        return isObject(type) == false;
    }

    public static boolean isNumber(int type){
        switch(type){
            case TokenType.Long:
            case TokenType.Double:
            case TokenType.QueryNumber:
                return true;
            default:
                return false;
        }
    }

    public static boolean isBool(int type){
        switch(type){
            case TokenType.Bool:
            case TokenType.QueryBool:
                return true;
            default:
                return false;
        }
    }

    public static boolean isString(int type){
        switch(type){
            case TokenType.Null:
            case TokenType.String:
                return true;
            default:
                return false;
        }
    }

    public static boolean isNumberOnlyOperator(int type){
        switch(type){
            case TokenType.Plus:
            case TokenType.Minus:
            case TokenType.Asterisk:
            case TokenType.Slash:
            case TokenType.Greater:
            case TokenType.EqualGreater:
            case TokenType.Less:
            case TokenType.EqualLess:
                return true;
            default:
                return false;
        }
    }

    public static boolean isNumberOperator(int type){
        switch(type){
            case TokenType.Plus:
            case TokenType.Minus:
            case TokenType.Asterisk:
            case TokenType.Slash:
                return true;
            default:
                return false;
        }
    }

    public static boolean isStringOnlyOperator(int type){
        switch(type){
            case TokenType.IndexOf:
            case TokenType.StartsWith:
            case TokenType.EndsWith:
            case TokenType.Find:
                return true;
            default:
                return false;
        }
    }

    public static boolean isBoolOnlyOperator(int type){
        switch(type){
            case TokenType.And:
            case TokenType.Or:
                return true;
            default:
                return false;
        }
    }

    public static String operatorToString(int type){
        switch(type){
            case Plus:
                return "+";
            case Minus:
                return "-";
            case Asterisk:
                return "*";
            case Slash:
                return "/";
            case Equal:
                return "==";
            case NotEqual:
                return "!=";
            case Greater:
                return ">";
            case EqualGreater:
                return "=>";
            case Less:
                return "<";
            case EqualLess:
                return "=<";
            case And:
                return "&&";
            case Or:
                return "||";
            case LeftParenthesis:
                return "(";
            case RightParenthesis:
                return ")";
            case IndexOf:
                return "indexOf";
            case StartsWith:
                return "startsWith";
            case EndsWith:
                return "endsWith";
            case Find:
                return "find";
        }
        return "";
    }
}
