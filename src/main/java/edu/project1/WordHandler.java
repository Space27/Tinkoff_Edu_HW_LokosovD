package edu.project1;

import org.jetbrains.annotations.NotNull;

public class WordHandler {

    private final String word;
    private String wordMask;

    public WordHandler(@NotNull String word) {
        this.word = word;
        wordMask = "*".repeat(word.length());
    }

    public String getWordMask() {
        return wordMask;
    }

    @Override
    public String toString() {
        return getWordMask();
    }

    public void openLetter(char letter) {
        char[] tmpMask = wordMask.toCharArray();

        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) == letter) {
                tmpMask[i] = letter;
            }
        }

        wordMask = String.valueOf(tmpMask);
    }

    public boolean isCharInWord(char letter) {
        return word.contains(String.valueOf(letter));
    }

    public boolean isCharInMask(char letter) {
        return wordMask.contains(String.valueOf(letter));
    }

    public boolean isWordEqMask() {
        return word.equals(wordMask);
    }
}
