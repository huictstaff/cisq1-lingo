package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Data;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;

import java.util.*;
import java.util.stream.Collectors;


@Data
public class Feedback {
    private final String attempt;
    private final List<Mark> marks;


    public static Feedback forCorrect(String attempt) {
        List<Mark> correctMarks = Collections.nCopies(attempt.length(), Mark.CORRECT);
        return new Feedback(attempt, correctMarks);
    }


    public static Feedback forInvalid(String attempt) {
        List<Mark> correctMarks = Collections.nCopies(attempt.length(), Mark.INVALID);
        return new Feedback(attempt, correctMarks);
    }

    public Feedback(String attempt, List<Mark> marks) {
        if (attempt.length() != marks.size())
            throw InvalidFeedbackException.guessLengthMissmatch(attempt.length(), marks.size());

        this.attempt = Objects.requireNonNull(attempt.toLowerCase());
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
