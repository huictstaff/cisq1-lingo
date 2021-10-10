package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Game {
    private int score;
    private List<Round> roundList;
    private GameState status;

    public enum GameState {
        PLAYING,
        STOPPED
    }

    public Game() {
        setScore(0);
    }

    public void startGame() {
        setStatus(GameState.PLAYING);
    }

    public void stopGame() {
        setStatus(GameState.STOPPED);
    }

    public void calculateScore() {
       setScore(((roundList.size()-1)*100));
    }


    public void setScore(int score) {
        this.score = score;
    }

    public void setStatus(GameState status) {
        this.status = status;
    }
}