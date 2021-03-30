package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Feedback {
    private final String guess;
    private final List<Mark> marks;

    public Feedback(String guess, List<Mark> marks) {
        this.guess = guess;
        this.marks = marks;
    }

    public static Feedback initialFeedback(String guess) {
        List<Mark> marks = new ArrayList<>();
        marks.add(Mark.CORRECT);
        for (int i = 1; i < guess.length(); i++) {
            marks.add(Mark.ABSENT);
        }
        return new Feedback(guess, marks);
    }

    public boolean wordIsGuessed() {
        if (!guessIsValid()) throw new InvalidFeedbackException();
        return marks.stream().allMatch(mark -> mark == Mark.CORRECT);
    }

    
    public boolean guessIsValid() {
        return guess != null && marks != null && guess.length() >= 5 && guess.length() <= 7 && marks.size() == guess.length();
    }

    public Hint giveHint(Hint previousHint) {
        if (guessIsValid()) {
            return new Hint(previousHint, this);
        } else throw new InvalidFeedbackException();
    }
}
