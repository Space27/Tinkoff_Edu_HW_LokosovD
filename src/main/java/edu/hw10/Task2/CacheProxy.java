package edu.hw10.Task2;

import java.lang.reflect.Proxy;

public final class CacheProxy {

    private CacheProxy() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(Object target, Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            interfaceClass.getInterfaces(),
            new CacheInvocationHandler(target)
        );
    }
}
