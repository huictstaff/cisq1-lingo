package nl.hu.cisq1.lingo.trainer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LingoGame implements Serializable {
    @Getter
    private int score;
    @Getter
    private List<Round> allRounds;

    public LingoGame() {
        this.score = 0;
        this.allRounds = new ArrayList<>();
    }

    public void newRound(String wordToGuess) {
        Round round = new Round(this.generateType(), wordToGuess, this);
        this.allRounds.add(round);
    }


    public RoundType generateType() {
        return switch (getTypeOfLastRound()) {
            case FIVELETTERS -> RoundType.SIXLETTERS;
            case SIXLETTERS -> RoundType.SEVENLETTERS;
            default -> RoundType.FIVELETTERS;
        };
    }

    @JsonIgnore
    private RoundType getTypeOfLastRound() {
        if (this.allRounds.isEmpty()) {
            return RoundType.SEVENLETTERS;
        }
        return this.allRounds.get(this.allRounds.size() - 1).getType();
    }

    @JsonIgnore
    public Round getLastRound() {
        if (this.allRounds.isEmpty()) {
            throw new RuntimeException("⏺ ⏺ ⏺ ⏺ Zero rounds found! ⏺ ⏺ ⏺ ⏺");
        }
        return this.allRounds.get(this.allRounds.size() - 1);
    }

    public void addScore(int score) {
        this.score += score;
    }
}
