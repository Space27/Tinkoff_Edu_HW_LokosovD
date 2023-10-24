package edu.project1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleHangman {

    private final int minWordLen = 3;

    public void run(String word) throws IllegalArgumentException {
        if (word.length() < minWordLen) {
            throw new IllegalArgumentException("The word must be at least" + minWordLen + "letters");
        }
        WordHandler wordHandler = new WordHandler(word);
        GameSession gameSession = new GameSession(wordHandler);

        log.info("Welcome to metal hangman!");
        while (gameSession.inGame()) {
            log.info("The word: " + wordHandler);
            log.info("Guess the letter: ");
            log.info(gameSession.guess(gameSession.getLetter()));
        }

        if (gameSession.isLose()) {
            log.info(MessageManager.defeat());
        } else if (gameSession.isWin()) {
            log.info(MessageManager.win());
        }
    }
}
