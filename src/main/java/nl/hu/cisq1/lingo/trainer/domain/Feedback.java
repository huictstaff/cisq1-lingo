package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Feedback {
    public Feedback(String attempt, List<Mark> marks) {
        this.attempt = attempt;
        this.marks = marks;

        if (attempt.length() != marks.size()) {
            throw new InvalidFeedbackException();
        }
    }

    private String attempt = null;
    private List<Mark> marks = null;

    public String getAttempt() {
        return attempt;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public boolean isWordGuessed() {
        return this.marks.stream().allMatch(Predicate.isEqual(Mark.CORRECT));
    }

    public String giveHint(String previousHint, String wordToGuess) {
        String hint = "";

        // Put correctly guessed characters into hint
        for (int i = 0; i < this.marks.size(); i++) {
            if (previousHint.charAt(i) != '.' || this.marks.get(i).equals(Mark.CORRECT)) {
                hint += wordToGuess.charAt(i);
            } else {
                hint += '.';
            }
        }

        return hint;
    }
}