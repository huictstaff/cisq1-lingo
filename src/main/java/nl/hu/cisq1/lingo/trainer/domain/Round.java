package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private String wordToGuess = null;
    private int attempts = -1;

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.attempts = 0;
    }

    public Feedback guessWord(String attempt) {
        // Generate marks
        List<Mark> marks = new ArrayList<>();
        for(int i = 0; i < attempt.length(); i++) {
            if(attempt.length() != this.wordToGuess.length()) {
                marks.add(Mark.INVALID);
            } else if(this.wordToGuess.charAt(i) == attempt.charAt(i)) {
                marks.add(Mark.CORRECT);
            } else if(this.wordToGuess.substring(i).contains(String.valueOf(attempt.charAt(i)))) {
                marks.add(Mark.PRESENT);
            } else {
                marks.add(Mark.ABSENT);
            }
        }

        // Generate feedback
        Feedback feedback = new Feedback(attempt, marks);
        this.attempts++;
        return feedback;
    }

    public String getWordToGuess() {
        return this.wordToGuess;
    }

    public int getAttempts() {
        return attempts;
    }
}
