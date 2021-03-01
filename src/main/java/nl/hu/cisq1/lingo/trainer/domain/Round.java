package nl.hu.cisq1.lingo.trainer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundState;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public String makeGuessAndGiveHint(String guess, Boolean wordExists) {
        this.tries += 1;
        if (!wordExists) {
            throw new RuntimeException("⏺ ⏺ ⏺ ⏺ Word does not exist! ⏺ ⏺ ⏺ ⏺");
        }
        Feedback feedback = new Feedback(
                guess,
                ConvertGuessToMarks.Converter(
                        this.wordToGuess,
                        guess
                ));
        this.allFeedback.add(feedback);

        if(this.tries == 5 && !feedback.isWordGuessed()) {
            this.wordIsNotGuessed();
        }

        else if (feedback.isWordGuessed()) {
            this.wordIsGuessed();
        }
        this.lastHint = feedback.giveHint(this.lastHint);
        return feedback.giveHint(this.lastHint);
    }
    public void wordIsNotGuessed() {
        this.roundState = RoundState.LOST;
    }

    public void checkIfRoundIsLostOrWon() {
        if (this.getRoundState().equals(RoundState.LOST)) {
            throw new RuntimeException("⏺ ⏺ ⏺ ⏺ Round is already Lost ⏺ ⏺ ⏺ ⏺");
        }
        else if (this.getRoundState().equals(RoundState.WON)) {
            throw new RuntimeException("⏺ ⏺ ⏺ ⏺ Round is already Won ⏺ ⏺ ⏺ ⏺");
        }
    }

    public void wordIsGuessed() {
        this.roundState = RoundState.WON;
        this.lingoGame.addScore(5 * (5 - tries) + 5);
    }
}
