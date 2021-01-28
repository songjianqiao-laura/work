package com.laura.framwork.dubbo;


import com.laura.framwork.Invocation;
import com.laura.framwork.Protocol;
import com.laura.framwork.Url;

public class DubboProtocol implements Protocol {

    @Override
    public void start(Url url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostName(), url.getPort());

    }

    @Override
    public String send(Url url, Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHostName(),url.getPort(), invocation);
    }
}
