package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundType;

import java.util.ArrayList;
import java.util.List;

public class Round {
    @Getter private int tries;
    @Getter private List<Feedback> allFeedback;
    @Getter private boolean roundOver;
    @Getter private RoundType type;
    @Getter private String wordToGuess;
    private Game game;

    public Round(RoundType type, String wordToGuess, Game game) {
        this.tries = 0;
        this.allFeedback = new ArrayList<>();
        this.roundOver = false;
        this.type = type;
        this.wordToGuess = wordToGuess;
        this.game = game;
    }

    public String makeGuessAndGiveHint(String guess) {
        this.tries += 1;
        if(this.tries == 5) {
            this.wordIsNotGuessed();
        }
        Feedback feedback = new Feedback(
                guess,
                ConvertGuessToMarks.Converter(
                        wordToGuess,
                        guess
                ));
        this.allFeedback.add(feedback);
        if (feedback.isWordGuessed()) {
            this.wordIsGuessed();
        }
        return feedback.giveHint();
    }
    public void wordIsNotGuessed() {
        this.roundOver = true;
        this.game.setGameOver(true);
    }

    public void wordIsGuessed() {
        this.roundOver = true;
        this.game.addScore(5 * (5 - tries) + 5);
    }
}
