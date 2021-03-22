package nl.hu.cisq1.lingo.trainer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundState;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundType;
import nl.hu.cisq1.lingo.trainer.exception.InvalidWordException;
import nl.hu.cisq1.lingo.trainer.exception.RoundException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Round")
@EqualsAndHashCode
@NoArgsConstructor
public class Round implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Getter
    private int tries;
    @Column
    @Getter
    private String lastHint;

    @Column
    @Getter
    private RoundState roundState;

    @Column
    @Getter
    private RoundType type;

    @Column
    @Getter
    private String wordToGuess;

    @OneToMany(cascade = CascadeType.ALL)
    @Getter
    private List<Feedback> allFeedback;

    public Round(RoundType type, String wordToGuess) {
        this.tries = 0;
        this.allFeedback = new ArrayList<>();
        this.roundState = RoundState.ONGOING;
        this.type = type;
        this.wordToGuess = wordToGuess;
        this.lastHint = Feedback.createFirstHint(this.wordToGuess);
    }

    public String makeGuessAndGiveHint(String guess, boolean wordExists) {
        this.checkIfRoundIsLostOrWon();
        this.tries += 1;

        if (Boolean.FALSE.equals(wordExists)) {
            throw new InvalidWordException("⏺ ⏺ ⏺ ⏺ Word does not exist! ⏺ ⏺ ⏺ ⏺");
        }

        Feedback feedback = new Feedback(
                guess,
                ConvertGuessToMarks.converter(
                        this.wordToGuess,
                        guess
                ));
        this.allFeedback.add(feedback);

        if (feedback.isWordGuessed()) {
            this.roundState = RoundState.WON;
        } else {
            if (this.tries > 4) {
                this.roundState = RoundState.LOST;
            }
        }

        this.lastHint = feedback.giveHint(this.lastHint);
        return feedback.giveHint(this.lastHint);
    }

    private void checkIfRoundIsLostOrWon() {
        if (this.roundState.equals(RoundState.LOST)) {
            throw new RoundException("⏺ ⏺ ⏺ ⏺ Round is already Lost ⏺ ⏺ ⏺ ⏺");
        } else if (this.roundState.equals(RoundState.WON)) {
            throw new RoundException("⏺ ⏺ ⏺ ⏺ Round is already Won ⏺ ⏺ ⏺ ⏺");
        }
    }
}
