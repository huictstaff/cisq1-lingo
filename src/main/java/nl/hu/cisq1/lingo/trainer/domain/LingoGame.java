package nl.hu.cisq1.lingo.trainer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundType;
import nl.hu.cisq1.lingo.trainer.exception.RoundException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LingoGame")
public class LingoGame implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Getter
    private int score;

    @OneToMany(cascade = CascadeType.ALL)
    @Getter
    private List<Round> allRounds;

    public LingoGame() {
        this.score = 0;
        this.allRounds = new ArrayList<>();
    }

    public void newRound(String wordToGuess) {
        Round round = new Round(this.generateType(), wordToGuess);
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
            throw new RoundException("⏺ ⏺ ⏺ ⏺ Zero rounds found! ⏺ ⏺ ⏺ ⏺");
        }
        return this.allRounds.get(this.allRounds.size() - 1);
    }

    public void addScore(int tries) {
            this.score += (5 * (5 - tries) + 5);
    }
}
