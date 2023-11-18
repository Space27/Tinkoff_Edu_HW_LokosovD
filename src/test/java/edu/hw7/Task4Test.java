package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {

    @Test
    @DisplayName("0 итераций в однопотоке")
    void calculatePi_shouldReturn0For0Iterations() {
        int iterations = 0;

        double result = Task4.calculatePi(iterations);

        assertThat(result)
            .isZero();
    }

    @Test
    @DisplayName("Постепенное увеличение итераций в однопотоке")
    void calculatePi_shouldReduceErrorWithIterationIncreasing() {
        List<Double> errorList = new ArrayList<>();

        for (long iterations = 1_000; iterations <= 10_000_000; iterations *= 100) {
            errorList.add(Task4.countTheTimeAndErrorForSimplePiCalculation(iterations).error());
        }

        assertThat(errorList)
            .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    @DisplayName("0 итераций в многопотоке")
    void calculatePiInParallel_shouldReturn0For0Iterations() {
        int iterations = 0;

        double result = Task4.calculatePiInParallel(iterations);

        assertThat(result)
            .isZero();
    }

    @Test
    @DisplayName("Постепенное увеличение итераций в многопотоке")
    void calculatePiInParallel_shouldReduceErrorWithIterationIncreasing() {
        List<Double> errorList = new ArrayList<>();

        for (long iterations = 1_000; iterations <= 10_000_000; iterations *= 100) {
            errorList.add(Task4.countTheTimeAndErrorParallelPiCalculation(iterations).error());
        }

        assertThat(errorList)
            .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    @DisplayName("Сравнение скорости одно- и многопоточной версии")
    void calculatePiInParallel_shouldBeFasterThanCalculatePi() {
        long iterations = 10_000_000;

        double simpleRes = Task4.countTheTimeAndErrorForSimplePiCalculation(iterations).time();
        double parallelRes = Task4.countTheTimeAndErrorParallelPiCalculation(iterations).time();

        assertThat(parallelRes)
            .isLessThan(simpleRes);

        System.out.println(simpleRes + "-simple");
        System.out.println(parallelRes + "-parallel");
        System.out.println(simpleRes / parallelRes + "-simple/parallel");
    }
}
