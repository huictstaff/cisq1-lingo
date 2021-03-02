package nl.hu.cisq1.lingo.words.domain;
import java.util.ArrayList;
import java.util.List;

public class Round {
    private String wordToGuess;
    private List<Feedback> roundFeedback = new ArrayList<>();

    public Round(String wordToGuess) {
        //todo misschien mooi om voor de hint een los object te maken zodat de hint niet meteen de eerste feedback hoeft te zijn.
        this.wordToGuess = wordToGuess;
        List<Rating> ratings = new ArrayList<>();
        ratings.add(Rating.CORRECT);
        for(int i = 1; i < wordToGuess.length(); i++){
            ratings.add(Rating.ABSENT);
        }
        roundFeedback.add(new Feedback(ratings));
    }

    public Feedback doGuess(String attempt){
        Feedback feedback = new Feedback(attempt, Feedback.generateRatings(attempt, wordToGuess));
        roundFeedback.add(feedback);
        return feedback;
    }
    public List<Feedback> getFeedbacks(){
        return roundFeedback;
    }
}
