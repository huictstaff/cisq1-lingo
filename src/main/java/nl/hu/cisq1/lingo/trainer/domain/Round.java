package nl.hu.cisq1.lingo.trainer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Round implements Serializable {
    private String wordToGuess = null;
    private int attempts = -1;

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.attempts = 0;
    }

    public List<Mark> generateMarks(String attempt) {
        List<Mark> marks = new ArrayList<>();
        String filteredWordToGuess = "";
        String filteredAttempt = "";

        // Check if invalid
        if (attempt.length() != this.wordToGuess.length()) {
            for (int i = 0; i < attempt.length(); i++) {
                marks.add(Mark.INVALID);
            }

            return marks;
        }

        // Generate without PRESENT marks
        for (int i = 0; i < attempt.length(); i++) {
            if (this.wordToGuess.charAt(i) == attempt.charAt(i)) {
                marks.add(Mark.CORRECT);
                filteredWordToGuess += '.';
            } else {
                marks.add(Mark.ABSENT);
                filteredWordToGuess += this.wordToGuess.charAt(i);
            }
        }

        // Generate PRESENT marks
        for (int i = 0; i < marks.size(); i++) {
            if (marks.get(i).equals(Mark.ABSENT) && filteredWordToGuess.contains(String.valueOf(attempt.charAt(i)))) {
                marks.set(i, Mark.PRESENT);
            }
        }

        return marks;
    }

    public Feedback guessWord(String attempt) {
        // Generate feedback
        Feedback feedback = new Feedback(attempt, this.generateMarks(attempt));
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
