package com.laura.framwork;

import java.util.HashMap;
import java.util.Map;

public class LogRegistry {

    static Map<String, Class> map = new HashMap<String, Class>();

    public static void registry(String className, Class proClass){
        map.put(className, proClass);
    }

    public static Class getRegistry(String className){
        return map.get(className);
    }

}
