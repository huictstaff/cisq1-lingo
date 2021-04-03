package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.GameLostException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundWonException;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private Hint hint;
    private String word;
    private List<Feedback> feedbackList;
    private List<Guess> guesses;
    private GameState gameState;

    public Round(String word) {
        this.word = word;
        this.feedbackList = new ArrayList<>();
        this.hint = giveFirstHint();
        this.guesses = new ArrayList<>(word.length());
        this.gameState = GameState.CONTINUE;
    }

    public Hint giveFirstHint() {
        ArrayList<Character> chars = new ArrayList<>();
        chars.add(this.word.charAt(0));
        for (int i = 0; i < word.length() - 1; i++) {
            chars.add('-');
        }
        return new Hint(chars);
    }

    public void makeGuess(String guess) {
        Guess attempt = new Guess(guess);
        if (attempt.validateGuess(guess, this.word)) {
            switch (this.gameState) {
                case LOST -> throw new GameLostException();
                case WON -> throw new RoundWonException();
                case CONTINUE -> {
                    this.guesses.add(attempt);
                    Feedback feedback = new Feedback(guess);
                    List<Mark> marks = feedback.toMarkArray(feedback.prepareFeedback(this.word, guess));
                    feedback.setMarks(marks);
                    feedbackList.add(feedback);
                    determineState(feedback);
                }
            }
        }
    }

    private void determineState() {
        if (guesses.size() > 4 && gameState != GameState.WON) {
            this.gameState = GameState.LOST;
        } else {
            this.gameState = GameState.CONTINUE;
        }
    }

    public void determineState(Feedback feedback) {
        if (feedback.isWordGuessed()) {
            this.gameState = GameState.WON;
        } else {
            determineState();
        }
    }

    public Hint getHint() {
        return hint;
    }

    public String getWord() {
        return word;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public List<Guess> getGuesses() {
        return guesses;
    }

    public GameState getGameState() {
        return gameState;
    }
}
