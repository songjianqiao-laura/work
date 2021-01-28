package com.laura.framwork;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShareMap {

    public static Map<String, List<Url>> map = new HashMap<String, List<Url>>();

    public static void registry(String interfaceName, Url url){
        List<Url> urls = map.get(interfaceName);
        if (urls == null){
            urls = new ArrayList<Url>();
        }
        urls.add(url);
        map.put(interfaceName,urls);
    }

    public static List<Url> getRegistry(String interfaceName){
        return map.get(interfaceName);
    }

    public static void saveFile(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<Url>> getFile(){
        try {
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<Url>>) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
