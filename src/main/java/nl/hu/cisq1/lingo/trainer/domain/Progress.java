package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Progress {
    private int score = -1;
    private int currentRound = -1;
    private List<String> hints = null;

    Progress() {
        score = 0;
        currentRound = 0;
        hints = new ArrayList<>(5);
    }

    public void increaseScore(int value) {
        score += value;
    }

    public void nextRound() {
        currentRound++;
        hints.clear();
    }

    public void addHint(String hint) {
        hints.add(hint);
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
