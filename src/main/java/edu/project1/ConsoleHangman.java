package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {

    private final Logger logger = LogManager.getLogger();
    private final int minWordLen = 3;

    public void run(String word) throws IllegalArgumentException {
        if (word.length() < minWordLen) {
            throw new IllegalArgumentException("The word must be at least" + minWordLen + "letters");
        }
        WordHandler wordHandler = new WordHandler(word);
        GameSession gameSession = new GameSession(wordHandler);

        logger.info("Welcome to metal hangman!");
        while (gameSession.inGame()) {
            logger.info("The word: " + wordHandler);
            logger.info("Guess the letter: ");
            logger.info(gameSession.guess(gameSession.getLetter()));
        }

        if (gameSession.isLose()) {
            logger.info(MessageManager.defeat());
        } else if (gameSession.isWin()) {
            logger.info(MessageManager.win());
        }
    }
}
