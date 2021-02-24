package trainer.domain;

import lombok.Data;
import trainer.domain.exception.InvalidFeedbackException;

import java.util.List;


@Data
public class Feedback {
    private final String attempt;
    private final List<Mark> marks;


    public Feedback(String attempt, List<Mark> marks) {
        if (attempt.length() != marks.size())
            throw InvalidFeedbackException.guessLengthMissmatch(attempt.length(), marks.size());

        this.attempt = attempt;
        this.marks = marks;
    }

    public boolean isWordGuessed() {
        boolean containsIncorrect = marks.stream().allMatch(i -> i == Mark.CORRECT);
        if (containsIncorrect) {
            return true;
        }
        return false;
    }


    public boolean isGuessValid() {
        boolean containsInvalid = marks.stream().anyMatch(i -> i == Mark.INVALID);
        if (!containsInvalid) {
            return true;
        }
        return false;
    }
}
