package nl.hu.cisq1.lingo.trainer.domain;

public class LingoGame {
    private Round round;
    private int currentWordLength;

    public LingoGame(Round round) {
        this.round = round;
        /*
        add exceptions when round is over
        and when user has lost
        */

    }
}
