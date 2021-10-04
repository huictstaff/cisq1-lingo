package nl.hu.cisq1.lingo.trainer.domain;

public class LingoGame {
    private LingoRound lingoRound;
    private int points;
    private int round;
    public LingoGame(String toGuess){
        lingoRound = new LingoRound(toGuess);
    }
}
