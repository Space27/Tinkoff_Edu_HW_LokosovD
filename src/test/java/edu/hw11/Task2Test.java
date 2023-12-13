package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    @BeforeAll
    public static void init(){
        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
            .describe("edu.hw11.ArithmeticUtils")
            .resolve();
        try (DynamicType.Unloaded<Object> objectUnloaded = new ByteBuddy()
            .redefine(typeDescription, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(ElementMatchers.named("sum"))
            .intercept(MethodCall.invoke(typeDescription.getDeclaredMethods().get(2))
                .withAllArguments())
            .make()) {
            objectUnloaded.load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);
        }
    }

    @ParameterizedTest
    @CsvSource({"0,1", "1,0", "0,0", "1,1", "2,1", "1,2", "-1,1", "0,-1", "-1,-1"})
    @DisplayName("Изменение функционала метода")
    void sum_shouldMultiplyNumbers(int a, int b) {
        int result = ArithmeticUtils.sum(a, b);
        int expected = a * b;

        assertThat(result)
            .isEqualTo(expected);
    }
}
