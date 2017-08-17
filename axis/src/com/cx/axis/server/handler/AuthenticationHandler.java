package com.cx.axis.server.handler;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.security.SecurityProvider;
import org.apache.axis.security.simple.SimpleSecurityProvider;
import org.apache.axis.utils.Messages;

/**
 * Created by Administrator on 2017/8/1.
 */
public class AuthenticationHandler extends BasicHandler {
    @Override
    public void invoke(MessageContext messageContext) throws AxisFault {
        SecurityProvider provider = (SecurityProvider) messageContext.getProperty("securityProvider");
        if (provider == null) {
            provider = new SimpleSecurityProvider();
            messageContext.setProperty("securityProvider", provider);
        }
        if (provider != null) {
            String userId = messageContext.getUsername();
            String password = messageContext.getPassword();

            //对用户进行认证，如果authUser==null，表示没有通过认证，
//            抛出Server.Unauthenticated异常。
            org.apache.axis.security.AuthenticatedUser authUser
                    = provider.authenticate(messageContext);
            if (authUser == null)
                throw new AxisFault("Server.Unauthenticated",
                        Messages.getMessage("cantAuth01", userId), null, null);
            //用户通过认证，把用户的设置成认证了的用户。
            messageContext.setProperty("authenticatedUser", authUser);
        }
    }
}
