package com.laura.framwork;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory<T> {

    public static <T> T getProxy(final Class interfaceClass){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation();
                invocation.setInterfaceName(interfaceClass.getClass().getName());
                invocation.setMethodName(method.getName());
                invocation.setParamTypes(method.getParameterTypes());
                invocation.setParams(args);
                Protocol protocol = ProtocolFactory.getProtocol();
                List<Url> registry = ShareMap.getRegistry(interfaceClass.getName());
                Url random = Balance.random(registry);
                String result = protocol.send(random, invocation);
                return result;
            }
        });
    }


}
