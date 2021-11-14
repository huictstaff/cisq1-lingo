package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Progress
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    private Game game;

    private int score;
    private int roundNumber;

   @Enumerated
    private GameStatus gameStatus;
    private ArrayList<Feedback> feedbacks;

    public Progress(Game game)
    {
        this.game = game;
        this.gameStatus = GameStatus.GAME_STARTED_WAITING_FOR_NEW_ROUND;
    }
    public Progress(){}

    // Getters to display data when printed in json

    public long getGameId() {
        return game.Id;
    }

    public int getScore() {
        return score;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
