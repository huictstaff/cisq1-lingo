package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Game {
    private int score;
    private List<Round> roundList;
    private GameState status;

    public Game() {
        this.score = 0;
    }

    public enum GameState {
        PLAYING,
        STOPPED
    }

    public void calculateScore() {
        this.score = (roundList.size()-1)*100;
    }

    public void stopGame() {
        this.status = GameState.STOPPED;
    }
}