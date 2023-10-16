package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameSession {

    private final Scanner scanner = new Scanner(System.in);
    private final Logger logger = LogManager.getLogger();
    private final char quitChar = '0';
    private final WordHandler wordHandler;
    private final int maxAttempts = 7;
    private int attempts;
    private boolean quitStatus = false;
    private String enteredLetters;

    public GameSession(WordHandler wordHandler) {
        this.wordHandler = wordHandler;
        attempts = 0;
        enteredLetters = "";
    }

    public char getLetter() {
        String input = scanner.nextLine();

        while (input.length() > 1 || !Character.isAlphabetic(input.charAt(0)) && input.charAt(0) != quitChar) {
            logger.info("Enter one letter");
            input = scanner.nextLine();
        }

        return input.charAt(0);
    }

    public boolean isLose() {
        return attempts >= maxAttempts;
    }

    public boolean isWin() {
        return wordHandler.isWordEqMask();
    }

    public boolean inGame() {
        return !(isLose() || isWin() || quitStatus);
    }

    public String guess(char letter) {
        String message;

        switch (guessStatus(letter)) {
            case QUIT:
                message = MessageManager.quit();
                quitStatus = true;
                break;
            case REPEAT:
                message = MessageManager.repeat();
                break;
            case GUESSED:
                message = MessageManager.guessed();
                wordHandler.openLetter(letter);
                break;
            default:
                ++attempts;
                message = MessageManager.notGuessed(attempts, maxAttempts);
        }

        enteredLetters += letter;

        return message;
    }

    private GuessStatus guessStatus(char letter) {
        if (letter == quitChar) {
            return GuessStatus.QUIT;
        } else if (wordHandler.isCharInMask(letter) || enteredLetters.contains(String.valueOf(letter))) {
            return GuessStatus.REPEAT;
        } else if (wordHandler.isCharInWord(letter)) {
            return GuessStatus.GUESSED;
        } else {
            return GuessStatus.NOT_GUESSED;
        }
    }
}
