package com.cx.axis.client;

import com.cx.axis.client.service.HelloWorldServiceLocator;
import com.cx.axis.client.service.HelloWorldSoapBindingStub;
import com.cx.axis.client.service.HelloWorld_PortType;

/**
 * Created by Administrator on 2017/8/1.
 */
public class TestClient {

    public static void main(String[] args) {
        try {
            HelloWorldServiceLocator locator = new HelloWorldServiceLocator();
//          Activator service = locator.get();
            HelloWorld_PortType service=locator.getHelloWorld();
            // If authorization is required
            ((HelloWorldSoapBindingStub)service).setUsername("cx");
            ((HelloWorldSoapBindingStub)service).setPassword("cx");

            System.out.println(service.sayHelloWorldFrom("feng xi"));
        } catch (javax.xml.rpc.ServiceException ex) {
            ex.printStackTrace();
        } catch (java.rmi.RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
