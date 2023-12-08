package edu.hw10.Task1.TypeGenerators;

import edu.hw10.Task1.Annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class StringGenerator extends TypeGenerator {

    public StringGenerator(TypeGenerator nextGenerator) {
        super(nextGenerator);
    }

    private static final Random RANDOM = new SecureRandom();
    private static final double NULL_CHANCE = 0.5;

    @Override
    public Object generateType(Parameter parameter) {
        if (parameter.getType().equals(String.class)) {
            double nullChance = NULL_CHANCE;
            Annotation[] annotations = parameter.getAnnotations();

            for (Annotation annotation : annotations) {
                if (annotation instanceof NotNull) {
                    nullChance = 1;
                    break;
                }
            }

            if (RANDOM.nextDouble() < nullChance) {
                return UUID.randomUUID().toString();
            } else {
                return null;
            }
        } else if (next != null) {
            return next.generateType(parameter);
        }

        return super.generateType(parameter);
    }
}
