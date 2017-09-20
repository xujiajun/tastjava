package cn.xujiajun.tastjava.core.aop;

import java.lang.reflect.Method;

public abstract class AfterHandler extends AbstractHandler {

    /**
     * Handles after the execution of method.
     *
     * @param proxy the proxy
     * @param method the method
     * @param args the args
     */
    public abstract void handleAfter(Object proxy, Method method, Object[] args);

    /* (non-Javadoc)
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = method.invoke(getTargetObject(), args);
        handleAfter(proxy, method, args);
        return result;
    }
}
