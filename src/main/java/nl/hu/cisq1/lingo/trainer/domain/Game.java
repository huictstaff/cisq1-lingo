package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.application.WordService;

public class Game {
    public void startGame(String word) {
        Round round = new Round(word);
    }
}
