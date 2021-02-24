package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Data;

import java.util.List;

@Data
public class Round {
    private final String WORD_TO_GUESS;
    private List<String> attempts;

    public Round(String wordToGuess, List<String> attempts) {
        this.WORD_TO_GUESS = wordToGuess;
        this.attempts = attempts;
    }

    public void addAttempt(String string) {

    }


    public Feedback getFeedbackOnLastAttempt() {
        return null;
    }
}
