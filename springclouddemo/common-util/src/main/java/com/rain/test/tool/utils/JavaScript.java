package com.rain.test.tool.utils;

//实现类

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaScript {
    ScriptEngineManager factory = new ScriptEngineManager();
    ScriptEngine engine = factory.getEngineByName("JavaScript");

    public String getMathValue(Map map, String option){
        String s = "";
        try {
            String [] ss = option.split("\\+|\\-|\\*");
            List list = new ArrayList();
            if(option.contains("(")){
                for (int i = 0; i <ss.length ; i++) {
                    if(ss[i].contains("(")){
                        ss[i]=ss[i].substring(1,ss[i].length());
                    }else if(ss[i].contains(")")){
                        ss[i]=ss[i].substring(0,ss[i].length()-1);
                    }
                    list.add(ss[i]);
                }
            }else {
                list = Arrays.asList(ss);
            }
            for (int i = 0; i <list.size() ; i++) {
                s = list.get(i).toString();
                if(map.containsKey(s)){
                    option = option.replace(s,map.get(s).toString());
                }else {
                    continue;
                }
            }
            if(judgeContainsStr(option) || "".equals(option)){
                s = option;
            }else {
                Object o = engine.eval(option);
                s = o.toString();
            }
        } catch (ScriptException e) {
            System.out.println("无法识别表达式");
            e.printStackTrace();
        }
        return s;
    }

    public boolean judgeContainsStr(String str) {
        String regex=".*[a-zA-Z]+.*";
        Matcher m= Pattern.compile(regex).matcher(str);
        return m.matches();
    }
}
