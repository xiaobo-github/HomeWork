package com.orakcool.hw_10.annotations;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

public class ContextProcessor {
    private static final Map<Class<?>, Object> SINGLETONS = new HashMap<>();

    @SneakyThrows
    public static void run(String scannerPath) {
        System.out.println("The context processor is running...");

        Reflections reflections = new Reflections(scannerPath, Scanners.values());

        checkSingleton(reflections);
        checkFieldWired(reflections);

        System.out.println("The context processor has completed\n");
    }

    private static void checkFieldWired(Reflections reflections) {
        Set<Field> fieldSet = reflections.getFieldsAnnotatedWith(Autowired.class);
        fieldSet.forEach(field -> {
            field.setAccessible(true);
            System.out.println("Autowired: [" + field.getType() + ']');
            try {
                field.set(null, createObject(field.getType()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void checkSingleton(Reflections reflections) {
        reflections.getTypesAnnotatedWith(Singleton.class)
                .forEach(singleton -> SINGLETONS.put(singleton, createObject(singleton)));
    }

    @SneakyThrows
    private static Object createObject(Class<?> type) {
        if (SINGLETONS.containsKey(type)) {
            return SINGLETONS.get(type);
        }

        Constructor<?>[] constructors = type.getConstructors();
        String typeName = '[' + type.getTypeName() + ']';

        if (constructors.length > 1) {
            throw new IllegalStateException(typeName + " has more than one constructor");
        }

        Constructor<?> constructor = constructors[0];

        if (constructor.getParameterCount() > 1) {
            throw new IllegalStateException(typeName + " has more than one constructor parameter(don`t supported now)");
        }

        if (constructor.getParameterCount() == 0) {
            return constructor.newInstance();
        }

        Arrays.stream(constructor.getDeclaredAnnotations()).filter(annotation -> annotation.annotationType().equals(Autowired.class)).findFirst().orElseThrow();

        Class<?> parameterType = constructor.getParameterTypes()[0];
        Object parameterObject = createObject(parameterType);

        return constructor.newInstance(parameterObject);
    }
}
