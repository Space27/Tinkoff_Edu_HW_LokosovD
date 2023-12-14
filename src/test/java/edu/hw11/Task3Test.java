package edu.hw11;

import edu.hw11.Task3.Fib;
import edu.hw11.Task3.FibRealization;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {

    private static Class<?> dynamicType;

    @BeforeAll
    public static void init() {
        try (DynamicType.Unloaded<Object> objectUnloaded = new ByteBuddy()
            .subclass(Object.class)
            .name("FibClass")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(FibRealization.INSTANCE))
            .make()) {
            dynamicType = objectUnloaded
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 5, 6, 10, 40, 45})
    @DisplayName("Метод должен высчитывать числа Фиббоначи")
    void fib_shouldCalculateFibNumber(int n)
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Object dynamicClass = dynamicType.getConstructors()[0].newInstance();
        Method fib = dynamicType.getDeclaredMethod("fib", int.class);

        long result = (long) fib.invoke(dynamicClass, n);
        long expected = Fib.fib(n);

        assertThat(result)
            .isEqualTo(expected);
    }
}
