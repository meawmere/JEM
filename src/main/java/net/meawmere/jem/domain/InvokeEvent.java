package net.meawmere.jem.domain;

import net.meawmere.jem.infrastructure.annotations.async;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

public record InvokeEvent(Class<?> eventClass, Method method, Object instance, boolean isStatic) {
    public void invoke(Object... objects) throws InvocationTargetException, IllegalAccessException {
        async isAsync = method.getAnnotation(net.meawmere.jem.infrastructure.annotations.async.class);
        
        if (isAsync != null) {
            if (isStatic) {
                CompletableFuture.supplyAsync(() -> {
                    try {
                        method.invoke(eventClass, objects);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                });
            } else {
                CompletableFuture.supplyAsync(() -> {
                    try {
                        method.invoke(instance, objects);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                });
            }
        } else {

            if (isStatic)
                method.invoke(eventClass, objects);
            else
                method.invoke(instance, objects);
        }
    }
}
