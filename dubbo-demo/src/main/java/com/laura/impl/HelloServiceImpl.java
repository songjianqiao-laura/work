package com.laura.impl;

import com.laura.service.HelloService;

public class HelloServiceImpl implements HelloService {


    public String sayHello(String name) {
        return "hello"+name;
    }
}
