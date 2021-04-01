package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String guess;
    @ElementCollection
    private List<Mark> marks;
    @ManyToOne
    @JoinColumn(name = "round_id", nullable = false)
    private Round round;

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
