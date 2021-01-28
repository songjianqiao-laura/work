package com.laura.framwork.http;



import com.alibaba.fastjson.JSONObject;
import com.laura.framwork.Invocation;
import com.laura.framwork.LogRegistry;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpHandler {

    public void handler(HttpServletRequest request, HttpServletResponse response){
        try {
            //处理逻辑
//            Invocation invocation = JSONObject.parseObject(request.getInputStream(), Invocation.class);
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            Invocation invocation = null;
            try {
                invocation = (Invocation)ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            LogRegistry logRegistry = new LogRegistry();
            Class registry = logRegistry.getRegistry(invocation.getInterfaceName());
            Method method = registry.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String result = (String) method.invoke(registry.newInstance(),invocation.getParams());
            IOUtils.write(result, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
