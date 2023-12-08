package edu.hw10.Task1;

import edu.hw10.Task1.TypeGenerators.IntGenerator;
import edu.hw10.Task1.TypeGenerators.StringGenerator;
import edu.hw10.Task1.TypeGenerators.TypeGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class RandomObjectGenerator {

    private final TypeGenerator typeGenerator;

    public RandomObjectGenerator() {
        List<TypeGenerator> generators = List.of(new StringGenerator(null));

        typeGenerator = new IntGenerator(null);

        typeGenerator.setNextByList(generators);
    }

    public Object nextObject(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor<?> constructor = constructors[0];

        for (Constructor<?> i : constructors) {
            if (i.getParameterTypes().length > constructor.getParameterTypes().length) {
                constructor = i;
            }
        }

        Parameter[] parameters = constructor.getParameters();
        Object[] parametersValue = new Object[parameters.length];

        for (int i = 0; i < parameters.length; ++i) {
            parametersValue[i] = typeGenerator.generateType(parameters[i]);
        }

        try {
            return constructor.newInstance(parametersValue);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    public Object nextObject(Class<?> clazz, String fabricMethod) {
        try {
            Method fabric = Arrays.stream(clazz.getMethods())
                .filter(method -> method.getName().equals(fabricMethod))
                .filter(method -> method.getReturnType().equals(clazz))
                .filter(method -> Modifier.isStatic(method.getModifiers()))
                .findFirst()
                .orElseThrow();

            Parameter[] parameters = fabric.getParameters();
            Object[] parametersValue = new Object[parameters.length];

            for (int i = 0; i < parameters.length; ++i) {
                parametersValue[i] = typeGenerator.generateType(parameters[i]);
            }

            return fabric.invoke(clazz, parametersValue);
        } catch (NoSuchElementException | InvocationTargetException | IllegalAccessException e) {
            return nextObject(clazz);
        }
    }
}
