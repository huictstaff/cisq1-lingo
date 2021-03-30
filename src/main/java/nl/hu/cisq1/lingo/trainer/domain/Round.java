package nl.hu.cisq1.lingo.trainer.domain;

import lombok.*;
import nl.hu.cisq1.lingo.trainer.domain.exception.MaximumAttemptsReachedException;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Round {
    private Word wordToGuess;
    private State state = State.IN_PROGRESS;
    private int attempts;
    private List<Feedback> allFeedback = new ArrayList<>();
    private List<Hint> allHints = new ArrayList<>();

    public Round(Word wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.allFeedback.add(Feedback.initialFeedback(wordToGuess.getValue()));
        this.allHints.add(Hint.initialHint(this.allFeedback.get(0).getMarks(), wordToGuess.getValue().charAt(0), wordToGuess.getLength()));
    }

    public List<Character> guess(String guess) {
        if (this.attempts < 5) {
            Validator validator = new Validator(guess, this.wordToGuess.getValue());
            Feedback feedback = new Feedback(guess, validator.validate());
            this.attempts += 1;
            this.state = getState(feedback.wordIsGuessed());
            this.allFeedback.add(feedback);
            Hint hint = feedback.giveHint(this.getPreviousHint());
            this.allHints.add(hint);
            return hint.getHint();
        } else {
            this.state = State.LOST;
            throw new MaximumAttemptsReachedException();
        }
    }

    private Hint getPreviousHint() {
        int index = this.allHints.size() - 1;
        return this.allHints.get(index);
    }

    private State getState(boolean isGuessed) {
        if (isGuessed) return State.GUESSED;
        return State.IN_PROGRESS;
    }

    public boolean isFinished() {
        return this.state != State.IN_PROGRESS;
    }
}
