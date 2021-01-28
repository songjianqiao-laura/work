package com.laura.framwork;

public interface Protocol {

    public String send(Url url, Invocation invocation);

    public void start(Url url);

}
