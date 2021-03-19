package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;

public class Round {
    private Score score;
    private Turn turn;
    private String word;

    public Round(String word) {
        this.word = word;
    }


}
