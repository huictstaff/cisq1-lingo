package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int score;
    private final List<Round> roundList;
    private GameState status;

    public enum GameState {
        PLAYING,
        STOPPED
    }

    public Game() {
        startGame();
        this.roundList = new ArrayList<>();
        setScore(0);
    }

    public void startGame() {
        setStatus(GameState.PLAYING);
    }

    public void stopGame() {
        calculateScore();
        setStatus(GameState.STOPPED);
    }

    public Round createRound(String wordToGuess) {
        if (this.status != GameState.STOPPED){
            if (getRoundList().isEmpty()) {
                Round round = new Round(wordToGuess);
                roundList.add(round);
                return round;
            } else {
                if (getLastRound().getAttempts() > 4) {
                    throw new RuntimeException("Max attempts was already reached last round, You've Lost! GAME OVER!");
                } else {
                    Round round = new Round(wordToGuess);
                    roundList.add(round);
                    return round;
                }
            }
        }else{
            throw new RuntimeException("You've Lost! you can't make a new round.");
        }
    }

    public void calculateScore() {
       setScore(((getAmountOfRounds() - 1) * 100));
    }

    public int getLastRoundsWordLength() {
        return getLastRound().getCurrentWordLength();
    }

    public Round getLastRound() {
        return roundList.get(roundList.size()-1);
    }

    public int getAmountOfRounds(){
        return roundList.size();
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStatus(GameState status) {
        this.status = status;
    }

    public String toString() {
        StringBuilder reply = new StringBuilder();
        if (status == GameState.PLAYING){
            reply.append("Game is currently ongoing, ");
        }else{
            reply.append("Game is over, ");
        }
        reply.append("score = ").append(getScore()).append(", amount of rounds = ").append(getAmountOfRounds()).append(", the round words: ");
        for (Round round : getRoundList()){
            reply.append(round.getGuessWord());
        }
        return reply.toString();
    }
}
//{}