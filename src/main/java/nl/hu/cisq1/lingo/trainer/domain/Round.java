package nl.hu.cisq1.lingo.trainer.domain;

import lombok.*;
import nl.hu.cisq1.lingo.trainer.domain.exception.MaximumAttemptsReachedException;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Round {
    private String wordToGuess;
    private State state = State.IN_PROGRESS;
    private int attempts;
    private List<Feedback> feedback = new ArrayList<>();

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public List<Character> guess(String guess) {
        if (this.attempts < 5) {
            Validator validator = new Validator(guess, this.wordToGuess);
            Feedback feedback = new Feedback(guess, validator.validate());
            this.state = feedback.wordIsGuessed() ? State.GUESSED : State.IN_PROGRESS;
            this.attempts += 1;
            this.feedback.add(feedback);
            return feedback.giveHint();
        } else {
            throw new MaximumAttemptsReachedException();
        }
    }

    public boolean isFinished() {
        return this.state != State.IN_PROGRESS;
    }
}
