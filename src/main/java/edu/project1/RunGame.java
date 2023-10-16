package edu.project1;

public final class RunGame {

    private RunGame() {
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        ConsoleHangman game = new ConsoleHangman();

        game.run(dictionary.getRandomWord());
    }
}
