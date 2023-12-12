package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    @Test
    @DisplayName("Создание класса с методом toString")
    void toString_shouldReturnExpectedString()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        String expected = "Hello, ByteBuddy!";
        Class<?> dynamicType;

        try (DynamicType.Unloaded<Object> objectUnloaded = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value(expected))
            .make()) {
            dynamicType = objectUnloaded
                .load(getClass().getClassLoader())
                .getLoaded();
        }

        Object dynamicClass = dynamicType.getConstructors()[0].newInstance();

        assertThat(dynamicClass.toString())
            .isNotNull()
            .isEqualTo(expected);
    }
}
