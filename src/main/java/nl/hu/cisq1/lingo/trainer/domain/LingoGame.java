package nl.hu.cisq1.lingo.trainer.domain;

public class LingoGame {
    private Round round;
    private User user;
    private int currentWordLength;

    public LingoGame(Round round, User user) {
        this.round = round;
        this.user = user;
        /*
        add exceptions when round is over
        and when user has lost
        */

    }
}
