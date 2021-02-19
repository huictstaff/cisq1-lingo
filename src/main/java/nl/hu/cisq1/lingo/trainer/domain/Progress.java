package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Progress {
    private int score = 0;
    private int currentRound = 0;
    private List<String> hints;

    public void increaseScore(int value) {
        this.score += value;
    }

    public void nextRound() {
        this.currentRound++;
    }

    public void addHint(String hint) {
        this.hints.add(hint);
    }

    public int getScore() {
        return score;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public List<String> getHints() {
        return hints;
    }
}
