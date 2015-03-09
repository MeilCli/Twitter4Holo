package com.twitter.meil_mitu.twitter4holo.query;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class QueryManager{

    private HashMap<String, IQueryModule<?>> modules = new HashMap<String, IQueryModule<?>>();
    private static final Query trueQuery = new Query("true", TokenType.Bool);
    private static final Query falseQuery = new Query("false", TokenType.Bool);
    private static final HashMap<String, Integer> operators = new HashMap<String, Integer>();

    static{
        operators.put("+", TokenType.Plus);
        operators.put("-", TokenType.Minus);
        operators.put("*", TokenType.Asterisk);
        operators.put("/", TokenType.Slash);
        operators.put("==", TokenType.Equal);
        operators.put("!=", TokenType.NotEqual);
        operators.put(">", TokenType.Greater);
        operators.put(">=", TokenType.EqualGreater);
        operators.put("<", TokenType.Less);
        operators.put("<=", TokenType.EqualLess);
        operators.put("And", TokenType.And);
        operators.put("and", TokenType.And);
        operators.put("&&", TokenType.And);
        operators.put("Or", TokenType.Or);
        operators.put("or", TokenType.Or);
        operators.put("||", TokenType.Or);
        operators.put("(", TokenType.LeftParenthesis);
        operators.put(")", TokenType.RightParenthesis);
        operators.put("indexof", TokenType.IndexOf);
        operators.put("indexOf", TokenType.IndexOf);
        operators.put("IndexOf", TokenType.IndexOf);
        operators.put("startswith", TokenType.StartsWith);
        operators.put("startsWith", TokenType.StartsWith);
        operators.put("StartsWith", TokenType.StartsWith);
        operators.put("endswith", TokenType.EndsWith);
        operators.put("endsWith", TokenType.EndsWith);
        operators.put("EndsWith", TokenType.EndsWith);
        operators.put("find", TokenType.Find);
        operators.put("Find", TokenType.Find);
    }

    public QueryManager(){
    }

    public void addModule(IQueryModule<?> module){
        modules.put(module.getParentName(), module);
    }

    public boolean match(Query query) throws Twitter4HoloException{
        Query q = rawMatch(query);
        if(q.Bool == null){
            return false;
        }
        return q.Bool.booleanValue();
    }

    public Query rawMatch(Query query) throws Twitter4HoloException{
        if(query.Type == TokenType.Bool){
            return query;
        }
        if(query.Type != TokenType.QueryBool && query.Type != TokenType.QueryNumber){
            return falseQuery;
        }
        if(query.Type == TokenType.QueryBool && query.Query.size() == 1){
            return rawMatch(query.Query.get(0));
        }
        if(query.Query.size() != 3){
            return falseQuery;
        }
        Query o = query.Query.get(1);
        Query q1 = query.Query.get(0);
        Query q2 = query.Query.get(2);
        return operate(o, q1, q2);
    }

    private Query operate(Query o, Query q1, Query q2) throws Twitter4HoloException{
        if(TokenType.isBoolOnlyOperator(o.Type) || TokenType.isBool(getType(q2))){
            if(TokenType.isBool(getType(q1)) == false || TokenType.isBool(getType(q2)) == false){
                return falseQuery;
            }
            if(q1.Type == TokenType.Object){
                q1 = new Query(q1.Module.getChildBool(q1.String));
            }
            if(q2.Type == TokenType.Object){
                q2 = new Query(q2.Module.getChildBool(q2.String));
            }
            if(q1.Type == TokenType.QueryBool){
                q1 = rawMatch(q1);
            }
            if(q1.Type != TokenType.Bool){
                throw new Twitter4HoloException("operate not bool");
            }
            switch(o.Type){
                case TokenType.And:
                    if(q1.Bool.booleanValue() == false){
                        return falseQuery;
                    }
                    if(q2.Type == TokenType.QueryBool){
                        q2 = rawMatch(q2);
                    }
                    if(q2.Type != TokenType.Bool){
                        throw new Twitter4HoloException("operate not bool");
                    }
                    return q2;
                case TokenType.Or:
                    if(q1.Bool.booleanValue() == true){
                        return trueQuery;
                    }
                    if(q2.Type == TokenType.QueryBool){
                        q2 = rawMatch(q2);
                    }
                    if(q2.Type != TokenType.Bool){
                        throw new Twitter4HoloException("operate not bool");
                    }
                    return q2;
                case TokenType.Equal:
                    if(q2.Type == TokenType.QueryBool){
                        q2 = rawMatch(q2);
                    }
                    if(q2.Type != TokenType.Bool){
                        throw new Twitter4HoloException("operate not bool");
                    }
                    return boolToQuery(q1.Bool.booleanValue() == q2.Bool.booleanValue());
                case TokenType.NotEqual:
                    if(q2.Type == TokenType.QueryBool){
                        q2 = rawMatch(q2);
                    }
                    if(q2.Type != TokenType.Bool){
                        throw new Twitter4HoloException("operate not bool");
                    }
                    return boolToQuery(q1.Bool.booleanValue() != q2.Bool.booleanValue());
            }
        }else if(TokenType.isStringOnlyOperator(o.Type) || TokenType.isString(getType(q2))){
            if(TokenType.isString(getType(q1)) == false || TokenType.isString(getType(q2)) == false){
                return falseQuery;
            }
            if(q1.Type == TokenType.Object){
                q1 = new Query(q1.Module.getChildString(q1.String), TokenType.String);
            }
            if(q2.Type == TokenType.Object){
                q2 = new Query(q2.Module.getChildString(q2.String), TokenType.String);
            }
            if(q1.String == null || q2.String == null){
                return falseQuery;
            }
            switch(o.Type){
                case TokenType.IndexOf:
                    return boolToQuery(q1.String.indexOf(q2.String) != -1);
                case TokenType.StartsWith:
                    return boolToQuery(q1.String.startsWith(q2.String));
                case TokenType.EndsWith:
                    return boolToQuery(q1.String.endsWith(q2.String));
                case TokenType.Find:
                    return boolToQuery(Pattern.compile(q2.String).matcher(q1.String).find());
                case TokenType.Equal:
                    return boolToQuery(q1.String.equals(q2.String));
                case TokenType.NotEqual:
                    return boolToQuery(q1.String.equals(q2.String) == false);
            }
        }else if(TokenType.isNumberOnlyOperator(o.Type) || TokenType.isNumber(getType(q2))){
            if(TokenType.isNumber(getType(q1)) == false || TokenType.isNumber(getType(q2)) == false){
                return falseQuery;
            }
            return operateNumber(o, q1, q2);
        }
        return falseQuery;
    }

    private Query operateNumber(Query o, Query q1, Query q2) throws Twitter4HoloException{
        if(q1.Type == TokenType.QueryNumber){
            q1 = rawMatch(q1);
        }
        if(q2.Type == TokenType.QueryNumber){
            q2 = rawMatch(q1);
        }
        if(q1.Type == TokenType.Object){
            Long l = q1.Module.getChildLong(q1.String);
            if(l != null){
                q1 = new Query(l);
            }else{
                q1 = new Query(q1.Module.getChildDouble(q1.String));
            }
        }
        if(q2.Type == TokenType.Object){
            Long l = q2.Module.getChildLong(q2.String);
            if(l != null){
                q2 = new Query(l);
            }else{
                q2 = new Query(q2.Module.getChildDouble(q2.String));
            }
        }
        boolean isDouble;
        if(q1.Type == TokenType.Double && q2.Type == TokenType.Double){
            isDouble = true;
        }else if(q1.Type == TokenType.Double){
            q2 = new Query(Double.valueOf(q2.Long));
            isDouble = true;
        }else if(q2.Type == TokenType.Double){
            q1 = new Query(Double.valueOf(q1.Long));
            isDouble = true;
        }else{
            isDouble = false;
        }
        if(isDouble == true){
            switch(o.Type){
                case TokenType.Plus:
                    return new Query(q1.Double.doubleValue() + q2.Double.doubleValue());
                case TokenType.Minus:
                    return new Query(q1.Double.doubleValue() - q2.Double.doubleValue());
                case TokenType.Asterisk:
                    return new Query(q1.Double.doubleValue() * q2.Double.doubleValue());
                case TokenType.Slash:
                    return new Query(q1.Double.doubleValue() / q2.Double.doubleValue());
                case TokenType.Greater:
                    return boolToQuery(q1.Double.doubleValue() > q2.Double.doubleValue());
                case TokenType.EqualGreater:
                    return boolToQuery(q1.Double.doubleValue() >= q2.Double.doubleValue());
                case TokenType.Less:
                    return boolToQuery(q1.Double.doubleValue() < q2.Double.doubleValue());
                case TokenType.EqualLess:
                    return boolToQuery(q1.Double.doubleValue() <= q2.Double.doubleValue());
                case TokenType.Equal:
                    return boolToQuery(q1.Double.doubleValue() == q2.Double.doubleValue());
                case TokenType.NotEqual:
                    return boolToQuery(q1.Double.doubleValue() != q2.Double.doubleValue());
            }
        }else{
            switch(o.Type){
                case TokenType.Plus:
                    return new Query(q1.Long.longValue() + q2.Long.longValue());
                case TokenType.Minus:
                    return new Query(q1.Long.longValue() - q2.Long.longValue());
                case TokenType.Asterisk:
                    return new Query(q1.Long.longValue() * q2.Long.longValue());
                case TokenType.Slash:
                    return new Query(q1.Long.longValue() / q2.Long.longValue());
                case TokenType.Greater:
                    return boolToQuery(q1.Long.longValue() > q2.Long.longValue());
                case TokenType.EqualGreater:
                    return boolToQuery(q1.Long.longValue() >= q2.Long.longValue());
                case TokenType.Less:
                    return boolToQuery(q1.Long.longValue() < q2.Long.longValue());
                case TokenType.EqualLess:
                    return boolToQuery(q1.Long.longValue() <= q2.Long.longValue());
                case TokenType.Equal:
                    return boolToQuery(q1.Long.longValue() == q2.Long.longValue());
                case TokenType.NotEqual:
                    return boolToQuery(q1.Long.longValue() != q2.Long.longValue());
            }
        }
        return falseQuery;
    }

    public static Query boolToQuery(boolean b){
        if(b == true){
            return trueQuery;
        }
        return falseQuery;
    }

    public Query parse(String text) throws Twitter4HoloException{
        ArrayList<Token> tokens = analysis(text);
        preCheck(tokens);
        Query query = parse(tokens);
        parseQueryNumber(query);// 数式をQueryNumberに格納
        parseAnd(query);// 暗示的Andを明示化
        parseQueryBool(query);//これでAndまたはOrの単純QueryBoolになったはず
        check(query);
        return query;
    }

    public Query rawParse(Query query) throws Twitter4HoloException{
        parseQueryNumber(query);// 数式をQueryNumberに格納
        parseAnd(query);// 暗示的Andを明示化
        parseQueryBool(query);//これでAndまたはOrの単純QueryBoolになったはず
        check(query);
        return query;
    }

    private Query parse(ArrayList<Token> list){
        ArrayList<Query> query = new ArrayList<Query>();
        for(int i = 0; i < list.size(); ){
            Token token = list.get(0);
            if(token.Type == TokenType.LeftParenthesis){
                list.remove(0);
                query.add(parse(list));
                continue;
            }
            if(token.Type == TokenType.RightParenthesis){
                list.remove(0);
                break;
            }
            query.add(new Query(token.Text, token.Type, token.Module));
            list.remove(0);
        }
        if(query.size() == 1){
            return query.get(0);
        }else if(query.size() == 0){
            return trueQuery;
        }
        return new Query(query);
    }

    private void parseQueryNumber(Query query) throws Twitter4HoloException{
        if(query.Type != TokenType.QueryBool && query.Type != TokenType.QueryNumber){
            //この時点にはQueryBoolにも数式が入っている
            return;
        }
        if(isCompletedQuery(query) == true){
            return;
        }
        ArrayList<Query> list = query.Query;
        Query q;
        Query q1;
        Query q2;
        ArrayList<Query> newList;
        for(int i = 0; i < list.size(); i++){
            q = list.get(i);
            if(q.Type == TokenType.QueryBool || q.Type == TokenType.QueryNumber){
                parseQueryNumber(q);
            }
            if(q.Type == TokenType.Asterisk || q.Type == TokenType.Slash){
                if(i == 0 || i == list.size() - 1){
                    throw new Twitter4HoloException("number operator side is null");
                }
                q1 = list.get(i - 1);
                q2 = list.get(i + 1);
                test(q, q1, q2);
                list.remove(i - 1);
                list.remove(i - 1);
                list.remove(i - 1);
                newList = new ArrayList<Query>();
                newList.add(q1);
                newList.add(q);
                newList.add(q2);
                list.add(i - 1, new Query(newList));
                i = i - 1;
                continue;
            }
        }
        for(int i = 0; i < list.size(); i++){
            q = list.get(i);
            if(q.Type == TokenType.Plus || q.Type == TokenType.Minus){
                if(i == 0 || i == list.size() - 1){
                    throw new Twitter4HoloException("number operator side is null");
                }
                q1 = list.get(i - 1);
                q2 = list.get(i + 1);
                test(q, q1, q2);
                list.remove(i - 1);
                list.remove(i - 1);
                list.remove(i - 1);
                newList = new ArrayList<Query>();
                newList.add(q1);
                newList.add(q);
                newList.add(q2);
                list.add(i - 1, new Query(newList));
                i = i - 1;
                continue;
            }
        }
    }

    private void parseAnd(Query query) throws Twitter4HoloException{
        if(query.Type != TokenType.QueryBool){
            return;
        }
        ArrayList<Query> list = query.Query;
        Query q1;
        Query q2 = null;
        for(int i = 0; i < list.size(); i++){
            q1 = list.get(i);
            if(q1.Type == TokenType.QueryBool){
                parseAnd(q1);
            }
            if(q2 != null && TokenType.isObject(q2.Type) && TokenType.isObject(q1.Type)){
                list.add(i, new Query(null, TokenType.And));
                q2 = null;
                continue;
            }
            q2 = q1;
        }
    }

    private void parseQueryBool(Query query) throws Twitter4HoloException{
        if(query.Type != TokenType.QueryBool){
            return;
        }
        ArrayList<Query> list = query.Query;
        Query q;
        Query q1;
        Query q2;
        ArrayList<Query> newList;
        for(int i = 0; i < list.size(); i++){
            q = list.get(i);
            if(q.Type == TokenType.QueryBool){
                parseQueryBool(q);
            }
        }
        for(int i = 0; i < list.size(); i++){
            q = list.get(i);
            if(TokenType.isOperator(q.Type) && TokenType.isBoolOnlyOperator(q.Type) == false){
                if(i == 0 || i == list.size() - 1){
                    throw new Twitter4HoloException("number operator side is null");
                }
                q1 = list.get(i - 1);
                q2 = list.get(i + 1);
                test(q, q1, q2);
                list.remove(i - 1);
                list.remove(i - 1);
                list.remove(i - 1);
                newList = new ArrayList<Query>();
                newList.add(q1);
                newList.add(q);
                newList.add(q2);
                list.add(i - 1, new Query(newList));
                i = i - 1;
                continue;
            }
        }
        for(int i = 0; i < list.size(); i++){
            q = list.get(i);
            if(q.Type == TokenType.And){
                if(i == 0 || i == list.size() - 1){
                    throw new Twitter4HoloException("and operator side is null");
                }
                q1 = list.get(i - 1);
                q2 = list.get(i + 1);
                test(q, q1, q2);
                list.remove(i - 1);
                list.remove(i - 1);
                list.remove(i - 1);
                newList = new ArrayList<Query>();
                newList.add(q1);
                newList.add(q);
                newList.add(q2);
                list.add(i - 1, new Query(newList));
                i = i - 1;
                continue;
            }
        }
        for(int i = 0; i < list.size(); i++){
            q = list.get(i);
            if(q.Type == TokenType.Or){
                if(i == 0 || i == list.size() - 1){
                    throw new Twitter4HoloException("or operator side is null");
                }
                q1 = list.get(i - 1);
                q2 = list.get(i + 1);
                test(q, q1, q2);
                list.remove(i - 1);
                list.remove(i - 1);
                list.remove(i - 1);
                newList = new ArrayList<Query>();
                newList.add(q1);
                newList.add(q);
                newList.add(q2);
                list.add(i - 1, new Query(newList));
                i = i - 1;
                continue;
            }
        }
    }

    private boolean isCompletedQuery(Query q) throws Twitter4HoloException{
        if(getType(q) == TokenType.Bool){
            return true;
        }
        if(q.Type == TokenType.QueryBool && q.Query.size() == 1){
            return true;
        }
        if(q.Type != TokenType.QueryBool && q.Type != TokenType.QueryNumber){
            return false;
        }
        if(q.Query.size() != 3){
            return false;
        }
        if(q.Query.size() < 3){
            throw new Twitter4HoloException("query size is less than 3" + q);
        }
        Query q1 = q.Query.get(0);
        Query q2 = q.Query.get(1);
        Query q3 = q.Query.get(2);
        if(TokenType.isOperator(q2.Type) == false){
            return false;
        }
        if(TokenType.isObject(q1.Type) == false){
            return false;
        }
        if(TokenType.isObject(q3.Type) == false){
            return false;
        }
        test(q2, q1, q3);
        return true;
    }

    private void preCheck(ArrayList<Token> list) throws Twitter4HoloException{
        int v = 0;
        for(Token token : list){
            switch(token.Type){
                case TokenType.LeftParenthesis:
                    v++;
                    break;
                case TokenType.RightParenthesis:
                    v--;
                    break;
                default:
                    break;
            }
            if(v < 0){
                throw new Twitter4HoloException(" ) not allow");
            }
        }
        if(v != 0){
            throw new Twitter4HoloException("() not allow");
        }
    }

    private void check(Query query) throws Twitter4HoloException{
        if(isCompletedQuery(query) == false){
            throw new Twitter4HoloException("not query" + query);
        }
        if(query.Type == TokenType.Bool){
            return;
        }
        if(query.Query.size() < 3){
            return;
        }
        //query.Query.size==3確定
        Query q1 = query.Query.get(0);
        Query q2 = query.Query.get(2);
        if(q1.Type == TokenType.QueryBool || q1.Type == TokenType.QueryNumber){
            check(q1);
        }
        if(q2.Type == TokenType.QueryBool || q2.Type == TokenType.QueryNumber){
            check(q2);
        }
    }

    private void test(Query o, Query q1, Query q2) throws Twitter4HoloException{
        int q1Type = getType(q1);
        int q2Type = getType(q2);
        if(TokenType.isNumberOnlyOperator(o.Type)){
            if(TokenType.isNumber(q1Type) == false || TokenType.isNumber(q2Type) == false){
                throw new Twitter4HoloException("number only operator is not non number");
            }
        }
        if(TokenType.isBoolOnlyOperator(o.Type)){
            if(TokenType.isBool(q1Type) == false || TokenType.isBool(q2Type) == false){
                throw new Twitter4HoloException("number only operator is not non bool");
            }
        }
        if(TokenType.isStringOnlyOperator(o.Type)){
            if(TokenType.isString(q1Type) == false || TokenType.isString(q2Type) == false){
                throw new Twitter4HoloException("number only operator is not non string");
            }
        }
        if(TokenType.isNumber(q1Type) != TokenType.isNumber(q2Type)){
            throw new Twitter4HoloException("operator side type is not equal");
        }
        if(TokenType.isString(q1Type) != TokenType.isString(q2Type)){
            throw new Twitter4HoloException("operator side type is not equal");
        }
        if(TokenType.isBool(q1Type) != TokenType.isBool(q2Type)){
            throw new Twitter4HoloException("operator side type is not equal");
        }
    }

    private int getType(Query q) throws Twitter4HoloException{
        if(q.Type != TokenType.Object){
            return q.Type;
        }
        Class c = q.Module.getChildClass(q.String);
        if(c.equals(String.class)){
            return TokenType.String;
        }else if(c.equals(Integer.class) || c.equals(Long.class)){
            return TokenType.Long;
        }else if(c.equals(Float.class) || c.equals(Double.class)){
            return TokenType.Double;
        }else if(c.equals(Boolean.class)){
            return TokenType.Bool;
        }
        throw new Twitter4HoloException("not find object type");
    }

    private ArrayList<Token> analysis(String text) throws Twitter4HoloException{
        ArrayList<Token> list = new ArrayList<Token>();
        int size = text.length();
        char c;
        StringBuilder sb = new StringBuilder();
        boolean isEscape = false;
        boolean isText = false;
        boolean isSpace = false;
        Token token;
        for(int i = 0; i < size; i++){
            c = text.charAt(i);
            switch(c){
                case '\\':
                    isSpace = false;
                    if(isEscape == true){
                        isEscape = false;
                        sb.append(c);
                    }else{
                        if(isText == false){
                            throw new Twitter4HoloException("escape not in text");
                        }
                        if(i + 1 < size && (text.charAt(i + 1) == '\\' || text.charAt(i + 1) == '"')){
                            isEscape = true;
                        }else{
                            throw new Twitter4HoloException("query escape error");
                        }
                    }
                    break;
                case '"':
                    if(isEscape == true){
                        isEscape = false;
                        sb.append(c);
                        break;
                    }
                    if(isText == false){
                        if(sb.length() > 0){
                            list.add(toToken(sb.toString()));
                            sb = new StringBuilder();
                        }
                        isText = true;
                    }else{
                        isText = false;
                        list.add(new Token(sb.toString(), TokenType.String));
                        sb = new StringBuilder();
                    }
                    isSpace = false;
                    break;
                case ' ':
                case '\n':
                case '\t':
                    if(isText == false){
                        if(isSpace == false && sb.length() > 0){
                            list.add(toToken(sb.toString()));
                            sb = new StringBuilder();
                        }
                        isSpace = true;
                    }else{
                        sb.append(c);
                    }
                    break;
                case '(':
                    isSpace = false;
                    if(isText == false){
                        if(sb.length() > 0){
                            list.add(toToken(sb.toString()));
                            sb = new StringBuilder();
                        }
                        list.add(new Token(null, TokenType.LeftParenthesis));
                    }else{
                        sb.append(c);
                    }
                    break;
                case ')':
                    isSpace = false;
                    if(isText == false){
                        if(sb.length() > 0){
                            list.add(toToken(sb.toString()));
                            sb = new StringBuilder();
                        }
                        list.add(new Token(null, TokenType.RightParenthesis));
                    }else{
                        sb.append(c);
                    }
                    break;
                default:
                    isSpace = false;
                    sb.append(c);
                    break;
            }
        }
        if(sb.length() > 0){
            list.add(toToken(sb.toString()));
        }
        return list;
    }

    private Token toToken(String text) throws Twitter4HoloException{
        if(operators.containsKey(text)){
            return new Token(null, operators.get(text));
        }
        if(text.equals("null")){
            return new Token(null, TokenType.Null);
        }
        if(text.equals("true")){
            return new Token("true", TokenType.Bool);
        }
        if(text.equals("false")){
            return new Token("false", TokenType.Bool);
        }
        if(startsNumber(text) == true){
            try{
                Long.parseLong(text);
                return new Token(text, TokenType.Long);
            }catch(NumberFormatException e){
            }
            try{
                Double.parseDouble(text);
                return new Token(text, TokenType.Double);
            }catch(NumberFormatException e){
            }
        }
        int index = text.indexOf('.');
        if(index == -1){
            throw new Twitter4HoloException("not have Objects period");
        }
        String parent = text.substring(0, index);
        String child = text.substring(index + 1, text.length());
        if(modules.containsKey(parent)){
            IQueryModule module = modules.get(parent);
            if(module.isChild(child)){
                return new Token(child, TokenType.Object, module);
            }else{
                throw new Twitter4HoloException("not allow child");
            }
        }else{
            throw new Twitter4HoloException("not allow parent");
        }
    }

    private boolean startsNumber(String text){
        if(text.length() == 0){
            return false;
        }
        switch(text.charAt(0)){
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
            default:
                return false;
        }
    }
}
