package edu.project1;

import java.util.Random;

public class Dictionary {
    private final String[] strings = {
        "metallica", "megadeth", "anthrax", "slayer", "helloween", "annihilator", "rammstein", "kreator", "sepultura",
        "scorpions", "accept", "ghost", "slipknot", "soad", "pantera", "sabaton", "powerwolf"
    };

    private final Random rnd = new Random();

    public String getRandomWord() {
        return strings[rnd.nextInt(strings.length)];
    }
}
