package edu.hw10.Task1;

import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;

public class AnnotationsClass {

    private final int a;
    private final String b;

    public AnnotationsClass(@Min(0) int a, @NotNull String b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public String getB() {
        return b;
    }
}
