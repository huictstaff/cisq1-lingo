package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private String wordToGuess = null;
    private List<String> attempts = null;
    private List<Feedback> feedbacks = null;

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.attempts = new ArrayList<>(5);
        this.feedbacks = new ArrayList<>(5);
    }

    public Feedback guessWord(String attempt) {
        // Generate marks
        List<Mark> marks = new ArrayList<>();
        for(int i = 0; i < this.wordToGuess.length(); i++) {
            if(this.wordToGuess.charAt(i) == attempt.charAt(i)) {
                marks.add(Mark.CORRECT);
            } else if(this.wordToGuess.substring(i).contains(String.valueOf(attempt.charAt(i)))) {
                marks.add(Mark.PRESENT);
            } else {
                marks.add(Mark.ABSENT);
            }
        }

        Feedback feedback = new Feedback(attempt, marks);
        this.feedbacks.add(feedback);
        this.attempts.add(attempt);

        return feedback;
    }

    public String getWordToGuess() {
        return this.wordToGuess;
    }

    public List<String> getAttempts() {
        return attempts;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
}
