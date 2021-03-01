package nl.hu.cisq1.lingo.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.hu.cisq1.lingo.domain.exception.RoundDoesNotBelongToGameException;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@EqualsAndHashCode
@ToString
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double score;
    private List<Round> rounds;

    public Game(Long id, double score, List<Round> rounds) {
        this.id = id;
        this.score = score;
        this.rounds = rounds;
    }

    public double addScore(Round round) {
        if (!this.rounds.contains(round)) {
            throw new RoundDoesNotBelongToGameException();
        }

        this.score += 5 * (5 - round.getAttempt()) + 5;

        return score;
    }
}