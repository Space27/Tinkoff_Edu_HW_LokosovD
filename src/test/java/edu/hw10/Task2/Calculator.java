package edu.hw10.Task2;

public interface Calculator {

    @Cache(persist = true)
    long fib(int number);

    long factorial(int number);
}
