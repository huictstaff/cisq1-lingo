package nl.hu.cisq1.lingo.trainer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.exception.MaximumAttemptsReachedException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "round")
public class Round {
    @Id
    @GeneratedValue(generator = "round_id_sequence")
    @SequenceGenerator(name="round_id_sequence", sequenceName = "round_id_seq")
    private Long id;
    private String wordToGuess;
    private State state = State.IN_PROGRESS;
    private int attempts;
    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private List<Feedback> allFeedback = new ArrayList<>();
    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private List<Hint> allHints = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        Feedback initialFeedback = Feedback.initialFeedback(wordToGuess);
        this.allFeedback.add(initialFeedback);
        Hint initialHint = Hint.initialHint(this.allFeedback.get(0).getMarks(), wordToGuess.charAt(0), wordToGuess.length());
        this.allHints.add(initialHint);

        initialFeedback.setRound(this);
        initialHint.setRound(this);
    }

    public List<Character> guess(String guess) {
        if (this.attempts < 5) {
            Validator validator = new Validator(guess, this.wordToGuess);
            Feedback feedback = new Feedback(guess, validator.validate());
            this.attempts += 1;
            this.state = getState(feedback.wordIsGuessed());
            this.allFeedback.add(feedback);
            Hint hint = feedback.giveHint(this.getPreviousHint());
            this.allHints.add(hint);

            feedback.setRound(this);
            hint.setRound(this);

            return hint.getHint();
        } else {
            throw new MaximumAttemptsReachedException();
        }
    }

    private Hint getPreviousHint() {
        int index = this.allHints.size() - 1;
        return this.allHints.get(index);
    }

    private State getState(boolean isGuessed) {
        if (isGuessed) return State.GUESSED;
        if (attempts == 5) return State.LOST;
        return State.IN_PROGRESS;
    }

    public boolean isFinished() {
        return this.state != State.IN_PROGRESS;
    }
}
