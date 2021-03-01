package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.AlreadyPlayingGameException;
import nl.hu.cisq1.lingo.trainer.domain.exception.LostGameException;
import nl.hu.cisq1.lingo.trainer.domain.exception.NotPlayingGameException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Progress progress = null;
    private List<Round> rounds = null;
    private GameStatus gameStatus = null;

    public Game() {
        this.progress = new Progress();
        this.rounds = new ArrayList<>();
        this.gameStatus = GameStatus.WAITING;
    }

    public void startNewRound(String wordToGuess) {
        if (gameStatus.equals(GameStatus.PLAYING)) {
            throw new AlreadyPlayingGameException();
        } else if (gameStatus.equals(GameStatus.LOST)) {
            throw new LostGameException();
        } else {
            rounds.add(new Round(wordToGuess));
            gameStatus = GameStatus.PLAYING;
            progress.nextRound(wordToGuess);
        }
    }

    public Feedback guessWord(String attempt) {
        // Exceptions
        if (gameStatus.equals(GameStatus.WAITING)) {
            throw new NotPlayingGameException();
        } else if (gameStatus.equals(GameStatus.LOST)) {
            throw new LostGameException();
        }

        // Temporary values
        Round currentRound = this.rounds.get(rounds.size() - 1);
        String previousHint = this.progress.getHints().get(this.progress.getHints().size() - 1);

        // Try guess & retrieve feedback
        Feedback feedback = currentRound.guessWord(attempt);

        // Add hint
        String hint = feedback.giveHint(previousHint, currentRound.getWordToGuess());
        this.progress.addHint(hint);

        // Check if player guessed the word right
        if(feedback.isWordGuessed()) {
            int score = 5 * (5 - currentRound.getAttempts().size()) + 5;
            this.progress.increaseScore(score);
            this.gameStatus = GameStatus.WAITING;
        } else if(currentRound.getAttempts().size() == 5) {
            this.gameStatus = GameStatus.LOST;
        }

        return feedback;
    }

    public Progress getProgress() {
        return progress;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public int getWordToGuessLength() {
        return this.rounds.get(rounds.size() - 1).getWordToGuess().length();
    }
}