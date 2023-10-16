package edu.project1;

public final class MessageManager {

    private MessageManager() {
    }

    public static String win() {
        return "You win!";
    }

    public static String defeat() {
        return "You lost!";
    }

    public static String guessed() {
        return "Hit";
    }

    public static String quit() {
        return "Game exit";
    }

    public static String repeat() {
        return "This letter has already been";
    }

    public static String notGuessed(int attempts, int maxAttempts) {
        return "Missed, mistake " + attempts + " out of " + maxAttempts + ".";
    }
}
