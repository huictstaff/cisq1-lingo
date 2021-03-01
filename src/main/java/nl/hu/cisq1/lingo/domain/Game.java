package nl.hu.cisq1.lingo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import nl.hu.cisq1.lingo.domain.exception.RoundAttemptLimitException;
import nl.hu.cisq1.lingo.domain.exception.RoundDoesNotBelongToGameException;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
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

    public void addScore(Round round, int attempts) {
        if (!this.rounds.contains(round)) {
            throw new RoundDoesNotBelongToGameException();
        }

        if(attempts > 5) {
            throw new RoundAttemptLimitException();
        }

        this.score += 5 * (5 - attempts) + 5;
    }

    public void newRound(Word word) {
        Round round = new Round(this.rounds.size() +1, word, new ArrayList<>());

        round.startRound();

        this.rounds.add(round);
    }
}