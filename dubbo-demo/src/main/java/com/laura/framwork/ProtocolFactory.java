package com.laura.framwork;

import com.laura.framwork.dubbo.DubboProtocol;
import com.laura.framwork.http.HttpProtocal;

public class ProtocolFactory {

    public static Protocol getProtocol(){
        String name = System.getProperty("protocal");
        if (name == null || "".equals(name)) name = "http";
        System.out.println(name);
        if ("http".equals(name)){
            return new HttpProtocal();
        }else if("netty".equals(name)){
            return new DubboProtocol();
        }
        return null;
    }


}
