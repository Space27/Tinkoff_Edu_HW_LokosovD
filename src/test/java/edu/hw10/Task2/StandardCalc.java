package edu.hw10.Task2;

public class StandardCalc implements Calculator {

    @Override
    public long fib(int number) {
        if (number < 2) {
            return number;
        }
        return fib(number - 1) + fib(number - 2);
    }

    @Override
    public long factorial(int number) {
        if (number == 0) {
            return 0;
        }
        return number * factorial(number - 1);
    }
}
