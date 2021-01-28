package com.laura.consumer;

import com.laura.framwork.Invocation;
import com.laura.framwork.Protocol;
import com.laura.framwork.ProtocolFactory;
import com.laura.framwork.Url;
import com.laura.framwork.http.HttpClient;
import com.laura.service.HelloService;

public class Consumer {

    public static void main(String[] args) {

        Invocation invocation = new Invocation();
        invocation.setInterfaceName(HelloService.class.getName());
        invocation.setMethodName("sayHello");
        invocation.setParamTypes(new Class[]{String.class});
        invocation.setParams(new Object[]{"送剑桥"});
        Protocol protocol = ProtocolFactory.getProtocol();
        Url url = new Url();
        url.setHostName("localhost");
        url.setPort(8080);
        String result = protocol.send(url, invocation);
        System.out.println(result);
    }

}
