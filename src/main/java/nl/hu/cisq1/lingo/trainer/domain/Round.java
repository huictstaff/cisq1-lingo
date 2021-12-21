package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.enums.State;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundOverException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Round {

    private final String answer;
    private State status;
    private String hint;

    private final List<String> attempts;
    private final List<Feedback> history;

    public Round(String answer) {
        this.answer = answer;
        this.status = State.ACTIVE;
        this.history = new ArrayList<>();
        this.attempts = new ArrayList<>();
        this.hint = startingHint();
    }

    public void guess(String attempt) {
        if (this.status != State.ACTIVE) {
            throw new RoundOverException();
        }

        Feedback feedback = new Feedback(this.answer, attempt);
        this.history.add(feedback);
        this.hint = feedback.giveHint(this.hint);
        this.attempts.add(attempt);

        if (feedback.isWordGuessed()) {
            this.status = State.WON;
        } else if (this.attempts.size() >= 5) {
            this.status = State.LOST;
        }

    }

    private String startingHint() {
        String[] letters = this.answer.split("");
        return letters[0] + ".".repeat(letters.length - 1);
    }

}
