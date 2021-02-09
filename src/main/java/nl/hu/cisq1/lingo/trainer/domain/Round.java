package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;

public class Round {
    private Score score;
    private Word word;
    private Feedback feedback;
    private int numberOfRounds;
    private String inputWord;

    public Round(Score score, Word word, Feedback feedback, String inputWord) {
        this.score = score;
        this.word = word;
        this.feedback = feedback;
        this.inputWord = inputWord;
    }
}
