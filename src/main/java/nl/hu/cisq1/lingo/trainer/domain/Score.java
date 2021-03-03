package nl.hu.cisq1.lingo.trainer.domain;

public class Score {
    private final Integer numberOfTurns;

    public Score(Integer numTurns) {
        this.numberOfTurns = numTurns;
    }

    public Integer calculate() {
        return 5 * (5 - this.numberOfTurns) + 5;
    }
}
