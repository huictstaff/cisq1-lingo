package nl.hu.cisq1.lingo.trainer.domain;

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
        for (int i = 0; i < word.length(); i++) {
            chars.add('-');
        }
        return new Hint(chars);
    }

    public void makeGuess(String guess) {
        Guess attempt = new Guess(guess);
        if (attempt.validateGuess(guess, this.word)) {
            this.guesses.add(attempt);
            Feedback feedback = new Feedback(guess);
            List<Mark> marks = feedback.toMarkArray(feedback.prepareFeedback(this.word, guess));
            feedback.setMarks(marks);
            feedbackList.add(feedback);
        }
    }

    public void determineState(Feedback feedback) {
        if (feedback.isWordGuessed()) {
            gameState = GameState.WON;
        } else if (guesses.size() > 4 && gameState != GameState.WON) {
            gameState = GameState.LOST;
        } else {
            gameState = GameState.CONTINUE;
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<Guess> getGuesses() {
        return guesses;
    }

    public String getWord() {
        return word;
    }

    public Hint getHint() {
        return hint;
    }
}
