package com.laura.framwork.http;

import com.laura.framwork.Invocation;
import com.laura.framwork.Protocol;
import com.laura.framwork.Url;

public class HttpProtocal implements Protocol {
    @Override
    public String send(Url url, Invocation invocation) {
        return new HttpClient().send(url.getHostName(),url.getPort(),invocation);
    }

    @Override
    public void start(Url url) {
        new HttpServer().start(url.getHostName(),url.getPort());
    }
}
