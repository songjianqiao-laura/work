package com.laura.provider;

import com.laura.framwork.*;
import com.laura.framwork.http.HttpServer;
import com.laura.impl.HelloServiceImpl;
import com.laura.service.HelloService;

public class Provider {

    public static void main(String[] args) {
        //需要本地注册
        LogRegistry logRegistry = new LogRegistry();
        logRegistry.registry(HelloService.class.getName(), HelloServiceImpl.class);


        //注册中心注册
        Url url = new Url();
        url.setHostName("localhost");
        url.setPort(8080);
        ShareMap.registry(HelloService.class.getName(), url);

        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }



}
