package edu.hw2;

import edu.hw2.Task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    Task4.CallingInfo testFunc() {
        return Task4.callingInfo();
    }

    @Test
    @DisplayName("Проверка на тесте")
    void callingInfo_ShouldReturnTestClassNameAndTestName() {
        Task4.CallingInfo callingInfo = Task4.callingInfo();

        assertThat(callingInfo.className())
            .isEqualTo("edu.hw2.Task4Test");
        assertThat(callingInfo.methodName())
            .isEqualTo("callingInfo_ShouldReturnTestClassNameAndTestName");
    }

    @Test
    @DisplayName("Проверка на внутренней функции")
    void callingInfo_ShouldReturnTestClassNameAndTestNameForInnerFunc() {
        Task4.CallingInfo callingInfo = testFunc();

        assertThat(callingInfo.className())
            .isEqualTo("edu.hw2.Task4Test");
        assertThat(callingInfo.methodName())
            .isEqualTo("testFunc");
    }
}
