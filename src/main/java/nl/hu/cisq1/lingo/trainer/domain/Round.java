package nl.hu.cisq1.lingo.trainer.domain;

public class Round {
    private String wordToGuess = null;
    private int attempts = -1;

    public Round(String wordToGuess, int attempts) {
        this.wordToGuess = wordToGuess;
        this.attempts = attempts;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public int getAttempts() {
        return attempts;
    }
}
