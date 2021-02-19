package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import java.util.List;
import java.util.function.Predicate;

public class Feedback {
    public Feedback(String attempt, List<Mark> marks) {
        this.attempt = attempt;
        this.marks = marks;

        if(attempt.length() != marks.size()) {
            throw new InvalidFeedbackException();
        }
    }

    private String attempt = null;
    private List<Mark> marks = null;

    public boolean isWordGuessed() {
        return this.marks.stream().allMatch(Predicate.isEqual(Mark.CORRECT));
    }
}