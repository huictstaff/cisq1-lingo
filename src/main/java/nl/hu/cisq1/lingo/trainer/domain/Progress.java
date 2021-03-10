package nl.hu.cisq1.lingo.trainer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Progress implements Serializable {
    private int score = -1;
    private int currentRound = -1;
    private List<Feedback> feedbacks = null;

    public Progress() {
        score = 0;
        currentRound = 0;
        feedbacks = new ArrayList<>(5);
    }

    public void increaseScore(int value) {
        score += value;
    }

    public void nextRound(String wordToGuess) {
        currentRound++;
        feedbacks.clear();

        // Compose first feedback
        String attempt = String.valueOf(wordToGuess.charAt(0));
        String initialHint = String.valueOf(wordToGuess.charAt(0));
        List<Mark> marks = new ArrayList<>(Arrays.asList(Mark.CORRECT));
        for(int i = 1; i < wordToGuess.length(); i++) {
            attempt += ' ';
            initialHint += '.';
            marks.add(Mark.ABSENT);
        }
        Feedback feedback = new Feedback(attempt, marks);
        feedback.generateHint(initialHint, wordToGuess);
        feedbacks.add(feedback);
    }

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public int getScore() {
        return score;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
}
