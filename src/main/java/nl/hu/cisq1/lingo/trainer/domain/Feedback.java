package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidHintException;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

public class Feedback implements Serializable {
    private String attempt = null;
    private String hint = null;
    private List<Mark> marks = null;

    public Feedback(String attempt, List<Mark> marks) {
        this.attempt = attempt;
        this.marks = marks;
        this.hint = "";

        if (attempt.length() != marks.size() && marks.stream().noneMatch(Predicate.isEqual(Mark.INVALID))) {
            throw new InvalidFeedbackException();
        }
    }

    public boolean isWordGuessed() {
        return this.marks.stream().allMatch(Predicate.isEqual(Mark.CORRECT));
    }

    public boolean isWordInvalid() {
        return this.marks.stream().anyMatch(Predicate.isEqual(Mark.INVALID));
    }

    public void generateHint(String previousHint, String wordToGuess) {
        // Return the previous hint when invalid
        if(isWordInvalid()) {
            this.hint = previousHint;
            return;
        }

        // Check if length is the same
        if (previousHint.length() != this.marks.size() || wordToGuess.length() != this.marks.size()) {
            throw new InvalidHintException();
        }

        // Result to return
        this.hint = "";

        // Add correctly guessed characters to the hint
        for (int i = 0; i < this.marks.size(); i++) {
            if (previousHint.charAt(i) != '.' || this.marks.get(i).equals(Mark.CORRECT)) {
                this.hint += wordToGuess.charAt(i);
            } else {
                this.hint += '.';
            }
        }
    }

    public String getAttempt() {
        return this.attempt;
    }

    public String getHint() {
        return this.hint;
    }

    public List<Mark> getMarks() {
        return this.marks;
    }
}