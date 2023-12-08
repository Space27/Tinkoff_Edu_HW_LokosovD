package edu.hw10.Task2;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CacheInvocationHandler implements InvocationHandler {

    private final Object target;
    private final Map<Method, Map<List<Object>, Object>> cacheMap;
    private final Set<Method> methods;
    private static final Path TMP_DIR = Path.of("cache");

    public CacheInvocationHandler(Object target) {
        this.cacheMap = new HashMap<>();
        this.methods = new HashSet<>();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Arrays.stream(method.getAnnotations()).anyMatch(annotation -> annotation instanceof Cache)) {
            Map<List<Object>, Object> tmpRes = cacheMap.get(method);
            Object result = tmpRes != null ? tmpRes.get(List.of(args)) : null;

            if (result == null) {
                result = method.invoke(target, args);
                cacheMap.put(method, Map.of(List.of(args), result));

                if (Arrays.stream(method.getAnnotations())
                    .anyMatch(annotation -> annotation instanceof Cache cache && cache.persist())) {
                    writeResult(method, result);
                }
            }

            return result;
        }

        return method.invoke(target, args);
    }

    private void writeResult(Method method, Object result) {
        Path path = TMP_DIR.resolve(target.getClass().getSimpleName()).resolve(method.getName() + ".txt");

        try {
            if (!methods.contains(method)) {
                if (Files.exists(path)) {
                    Files.delete(path);
                }
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                methods.add(method);
            }

            Files.writeString(path, result.toString() + '\n', StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
