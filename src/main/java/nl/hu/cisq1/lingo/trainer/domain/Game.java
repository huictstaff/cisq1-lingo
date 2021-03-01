package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.AlreadyGuessedException;
import nl.hu.cisq1.lingo.trainer.domain.exception.AlreadyPlayingException;
import nl.hu.cisq1.lingo.trainer.domain.exception.LostGameException;
import nl.hu.cisq1.lingo.trainer.domain.exception.NotPlayingException;

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
            throw new AlreadyPlayingException();
        } else if (gameStatus.equals(GameStatus.LOST)) {
            throw new LostGameException();
        } else {
            rounds.add(new Round(wordToGuess));
            gameStatus = GameStatus.PLAYING;
            progress.nextRound();

            // Compose first hint
            String hint = "";
            for(int i = 0; i < wordToGuess.length(); i++) {
                hint += ((i == 0) ? wordToGuess.charAt(i) : '.');
            }
            progress.addHint(hint);
        }
    }

    public Feedback guessWord(String attempt) {
        // Temporary values
        Round currentRound = this.rounds.get(rounds.size() - 1);

        // Exceptions
        if (gameStatus.equals(GameStatus.WAITING)) {
            throw new NotPlayingException();
        } else if (gameStatus.equals(GameStatus.LOST)) {
            throw new LostGameException();
        } else if(currentRound.getAttempts().contains(attempt)) {
            throw new AlreadyGuessedException();
        }

        // Try guess & retrieve feedback
        Feedback feedback = currentRound.guessWord(attempt);

        // Add hint
        String previousHint = this.progress.getHints().get(this.progress.getHints().size() - 1);
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
}