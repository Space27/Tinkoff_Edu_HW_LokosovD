package edu.hw10.TypeGenerators;

import edu.hw10.Annotations.Max;
import edu.hw10.Annotations.Min;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.security.SecureRandom;
import java.util.Random;

public class IntGenerator extends TypeGenerator {

    public IntGenerator(TypeGenerator nextGenerator) {
        super(nextGenerator);
    }

    private static final Random RANDOM = new SecureRandom();

    @Override
    public Object generateType(Parameter parameter) {
        if (parameter.getType().equals(int.class) || parameter.getType().equals(Integer.class)) {
            int max = Integer.MAX_VALUE;
            int min = Integer.MIN_VALUE;
            Annotation[] annotations = parameter.getAnnotations();

            for (Annotation annotation : annotations) {
                if (annotation instanceof Min minValue && minValue.value() < max) {
                    min = minValue.value();
                } else if (annotation instanceof Max maxValue && maxValue.value() > min) {
                    max = maxValue.value();
                }
            }

            return RANDOM.nextInt(min, max);
        } else if (next != null) {
            return next.generateType(parameter);
        }

        return super.generateType(parameter);
    }
}
