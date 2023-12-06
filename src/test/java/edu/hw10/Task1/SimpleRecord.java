package edu.hw10.Task1;

public record SimpleRecord(int a, String b) {

    public static SimpleRecord create(String b, int a) {
        return new SimpleRecord(a, b);
    }
}
