package nl.hu.cisq1.lingo.trainer.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString @EqualsAndHashCode
public class Hint {
    private final String wordToGuess;
    private Feedback feedback;
    private char[] previousHint;

    public Hint( String wordToGuess, Feedback feedback) {
        this.wordToGuess = wordToGuess;
        this.feedback = feedback;
    }

    public char[] giveHint() {
        char[] at = feedback.attempt.toCharArray();
        char[] w = wordToGuess.toCharArray();
        return null;
    }

}
