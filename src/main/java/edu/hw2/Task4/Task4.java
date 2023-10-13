package edu.hw2.Task4;

public final class Task4 {

    private Task4() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement method = Thread.currentThread().getStackTrace()[2];

        return new CallingInfo(method.getClassName(), method.getMethodName());
    }

    public record CallingInfo(String className, String methodName) {
    }
}
