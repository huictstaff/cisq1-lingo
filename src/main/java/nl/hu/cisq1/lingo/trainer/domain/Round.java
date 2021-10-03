package nl.hu.cisq1.lingo.trainer.domain;

public class Round {

    private String wordToGuess;
    private int attempts = 0;

    public Round(String wordToGuess){
        this.wordToGuess = wordToGuess;
    }
}