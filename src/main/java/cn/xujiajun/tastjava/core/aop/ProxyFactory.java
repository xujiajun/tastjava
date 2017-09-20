package cn.xujiajun.tastjava.core.aop;

import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {

    /**
     * Gets the proxy.
     *
     * @param targetObject the target object
     * @param handlers the handlers
     * @return the proxy
     */
    public static Object getProxy(Object targetObject,
                                  List<AbstractHandler> handlers) {
        Object proxyObject = null;
        if (handlers.size() > 0) {
            proxyObject = targetObject;
            for (int i = 0; i < handlers.size(); i++) {
                handlers.get(i).setTargetObject(proxyObject);
                proxyObject = Proxy.newProxyInstance(targetObject.getClass()
                        .getClassLoader(), targetObject.getClass()
                        .getInterfaces(), handlers.get(i));
            }
            return proxyObject;
        } else {
            return targetObject;
        }
    }
}