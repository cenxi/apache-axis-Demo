package com.cx.axis.server;

import org.apache.axis.MessageContext;

/**
 * Created by Administrator on 2017/8/1.
 */
public class HelloWorld {
  public String sayHelloWorldFrom(String from) {
    String result = "Hello, world, from " + from;
    System.out.println(result);
    return result;
  }
}
