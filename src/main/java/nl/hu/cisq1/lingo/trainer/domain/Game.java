package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundType;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {
    @Getter
    private UUID gameId;
    @Getter
    private int score;
    @Getter
    private List<Round> allRounds;
    @Setter @Getter
    private Boolean gameOver;

    public Game() {
        this.gameId = UUID.randomUUID();
        this.score = 0;
        this.allRounds = new ArrayList<>();
        this.gameOver = false;

    }

    public void newRound(String wordToGuess) {
        if (this.gameOver == true) {
            throw new RuntimeException("You have LOST this game, please start a new one");
        }
        Round round = new Round(this.generateType(), wordToGuess, this);
        this.allRounds.add(round);
    }


    public RoundType generateType() {
        try {
            switch (this.allRounds.get(allRounds.size() - 1).getType()) {
                case FIVELETTERS:
                    return RoundType.SIXLETTERS;
                case SIXLETTERS:
                    return RoundType.SEVENLETTERS;
                default:
                    return RoundType.FIVELETTERS;
            }
        } catch (IndexOutOfBoundsException ioe) {
            return RoundType.FIVELETTERS;
        }
    }

    public void addScore(int score) {
        this.score += score;
    }
}
