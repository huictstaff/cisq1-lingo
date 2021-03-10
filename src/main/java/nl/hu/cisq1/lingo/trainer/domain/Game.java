package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

// het starten van een nieuwe ronde en het raden van woorden binnen een ronde.
@Entity
@Table(name = "game")
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private GameStatus gameStatus;

    @Lob
    private Progress progress;

    @Lob
    private ArrayList<Round> rounds;

    @Lob
    private Integer score;

    public Game() {
        this.score = 0;
        this.gameStatus = GameStatus.WAITING_FOR_ROUND;
        this.progress = new Progress();
        this.rounds = new ArrayList<Round>();
    }

    public void startNewRound(Word wordToGuess) {
        if (gameStatus != GameStatus.WAITING_FOR_ROUND) {
            throw new IllegalStateException("Current game status doesn't allow this action. Status: " + gameStatus + ".");
        }
        // Make New Round
        Round round = new Round(wordToGuess);

        // Add to array of rounds
        rounds.add(new Round(wordToGuess));

        // Change the GameStatus to Playing
        gameStatus = GameStatus.PLAYING;

        // Progress to the next round
        progress.progressRound();

        // Set new progress data
        progress.saveNewProgress(round.giveHint(),round.getFeedbackHistory());
    }

    public void guess(String attempt) {
        if (gameStatus != GameStatus.PLAYING) {
            throw new IllegalStateException("Current game status doesn't allow this action. Status: " + gameStatus + ".");
        }
        // Get the round
        Round round = rounds.get(rounds.size()-1);

        // Play the guess
        round.guess(attempt);

        // Change the progress of the game based on the new round information
        progress.saveNewProgress(round.giveHint(),round.getFeedbackHistory());

        // If word guessed; progress the round
        if (progress.getLastFeedback().isWordGuessed()) {
            progress.progressRound();
            gameStatus = GameStatus.WAITING_FOR_ROUND;
        }
        // If limit is reached; game eliminated
        if(round.attemptLimitReached()) {
            gameStatus = GameStatus.ELIMINATED;
        }
    }

    public Progress getProgress() {
        return progress;
    }

    public boolean isPlayerEliminated() {
        return gameStatus == GameStatus.ELIMINATED;
    }

    public boolean isPlaying() {
        return gameStatus == GameStatus.PLAYING;
    }






    public ArrayList<Round> getRounds() {
        return rounds;
    }
}
