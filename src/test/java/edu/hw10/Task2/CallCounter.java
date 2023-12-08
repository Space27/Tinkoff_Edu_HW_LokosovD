package edu.hw10.Task2;

public class CallCounter extends StandardCalc {

    private int callCount;
    private final Calculator calculator;

    public CallCounter(Calculator calculator) {
        this.callCount = 0;
        this.calculator = calculator;
    }

    @Override
    public long fib(int number) {
        ++callCount;
        return calculator.fib(number);
    }

    @Override
    public long factorial(int number) {
        ++callCount;
        return calculator.factorial(number);
    }

    public int getCallCount() {
        return callCount;
    }
}
