package nl.hu.cisq1.lingo.trainer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundState;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundType;
import nl.hu.cisq1.lingo.trainer.exception.InvalidWordException;
import nl.hu.cisq1.lingo.trainer.exception.RoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class Round implements Serializable {
    @Getter private int tries;
    @Getter private List<Feedback> allFeedback;
    @Getter private String lastHint;
    @Getter private RoundState roundState;
    @Getter private RoundType type;
    @Getter private String wordToGuess;
    private LingoGame lingoGame;

    public Round(RoundType type, String wordToGuess, LingoGame lingoGame) {
        this.tries = 0;
        this.allFeedback = new ArrayList<>();
        this.roundState = RoundState.ONGOING;
        this.type = type;
        this.wordToGuess = wordToGuess;
        this.lastHint = Feedback.createFirstHint(this.wordToGuess);
        this.lingoGame = lingoGame;
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
            this.wordIsGuessed();
        } else {
            if (this.tries > 4) {
                this.wordIsNotGuessed();
            }
        }

        this.lastHint = feedback.giveHint(this.lastHint);
        return feedback.giveHint(this.lastHint);
    }

    private void checkIfRoundIsLostOrWon() {
        if (this.getRoundState().equals(RoundState.LOST)) {
            throw new RoundException("⏺ ⏺ ⏺ ⏺ Round is already Lost ⏺ ⏺ ⏺ ⏺");
        }
        else if (this.getRoundState().equals(RoundState.WON)) {
            throw new RoundException("⏺ ⏺ ⏺ ⏺ Round is already Won ⏺ ⏺ ⏺ ⏺");
        }
    }

    private void wordIsNotGuessed() {
        this.roundState = RoundState.LOST;
    }

    private void wordIsGuessed() {
        this.roundState = RoundState.WON;
        this.lingoGame.addScore(5 * (5 - tries) + 5);
    }
}
