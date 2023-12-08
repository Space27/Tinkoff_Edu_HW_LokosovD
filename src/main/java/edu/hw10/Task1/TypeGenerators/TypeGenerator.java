package edu.hw10.Task1.TypeGenerators;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.List;

public abstract class TypeGenerator {

    protected TypeGenerator next;

    public TypeGenerator(TypeGenerator nextGenerator) {
        this.next = nextGenerator;
    }

    public void setNext(TypeGenerator nextGenerator) {
        this.next = nextGenerator;
    }

    public void setNextByList(List<TypeGenerator> nextGenerators) {
        this.next = nextGenerators.getFirst();

        if (nextGenerators.size() > 1) {
            nextGenerators.getFirst().setNextByList(nextGenerators.subList(1, nextGenerators.size()));
        }
    }

    public Object generateType(Parameter parameter) {
        if (parameter.getType().isPrimitive()) {
            return switch (parameter.getType().getTypeName()) {
                case "boolean" -> false;
                case "char" -> ' ';
                default -> (byte) 0;
            };
        }
        Constructor<?>[] constructors = parameter.getType().getConstructors();

        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameters().length == 0) {
                try {
                    return constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    break;
                }
            }
        }
        return null;
    }
}
