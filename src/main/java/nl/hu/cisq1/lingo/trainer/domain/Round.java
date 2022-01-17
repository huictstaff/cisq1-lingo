package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.exception.WordNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private String wordToGuess;
    private int attempts;
    private List<Feedback> feedbackHistory = new ArrayList<>();
    private String laatsteHint;
    private Game game;

    public Round(String wordToGuess, int attempts) {
        this.wordToGuess = wordToGuess;
        this.attempts = attempts;
        makeFirstHint(wordToGuess);
    }

    public void guess(String attempt){
        List<Mark> marks = new ArrayList<>();
        Feedback feedback;
        if(attempt.length() == wordToGuess.length() && attempts <5){
            for (var i = 0; i < wordToGuess.length(); i++){
                if(attempt.charAt(i) == wordToGuess.charAt(i)){
                    marks.add(Mark.Correct);
                }
                else if(wordToGuess.contains(String.valueOf(attempt.charAt(i)))){
                    marks.add(Mark.Present);
                }
                else marks.add(Mark.Absent);
            }
        }
        else {
            for(var i = 0; i < attempt.length();i++){
                marks.add(Mark.Invalid);
            }
           // throw new WordNotFoundException(attempt);
        }
        feedback = new Feedback(attempt,marks);
        System.out.println(feedback);
        feedbackHistory.add(feedback);
        attempts++;
    }

    void addFeedback(Feedback feedback){
        this.feedbackHistory.add(feedback);
    }

    public List<Feedback> getFeedbackHistory(){
        return feedbackHistory;
    }
    private void makeFirstHint(String wordToGuess){
        StringBuilder hint = new StringBuilder(wordToGuess);
        for(var i = 1; i < wordToGuess.length();i++){
            hint.setCharAt(i, '.');
        }
        this.laatsteHint = String.valueOf(hint);
    }

    public String giveHint() throws Exception {
        Feedback feedback = feedbackHistory.get(feedbackHistory.size() - 1);
        return feedback.giveHint(laatsteHint);
    }

}
