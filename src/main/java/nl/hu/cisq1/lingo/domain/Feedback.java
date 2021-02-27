package nl.hu.cisq1.lingo.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.hu.cisq1.lingo.domain.exception.InvalidFeedbackException;

import java.util.List;

@EqualsAndHashCode
@ToString
public class Feedback {
    private final String attempt;
    private final List<Mark> marks;
    private Hint hint;

    public Feedback(String attempt, List<Mark> marks) {
        if (attempt.length() != marks.size()) {
            throw new InvalidFeedbackException();
        }

        this.attempt = attempt;
        this.marks = marks;
    }

    public boolean isWordGuessed() {
        return marks.stream().allMatch(mark -> mark.equals(Mark.CORRECT));
    }

    public boolean guessIsInvalid() {
        return marks.stream().allMatch(mark -> mark.equals(Mark.INVALID));
    }
}
