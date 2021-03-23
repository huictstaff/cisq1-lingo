package nl.hu.cisq1.lingo.domain;
import lombok.Data;

import nl.hu.cisq1.lingo.domain.Enums.Rating;
import nl.hu.cisq1.lingo.domain.exception.InvalidGuessLengthException;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenGuessException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Round {
    @Id
    @GeneratedValue
    private long id;
    private String wordToGuess;
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name = "round_id")
    private List<Feedback> roundFeedback = new ArrayList<>();
    private int maxGuesses = 5;
    public Round(){}

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        List<Rating> ratings = new ArrayList<>();
        ratings.add(Rating.CORRECT);
        for(int i = 1; i <= wordToGuess.length(); i++){
            ratings.add(Rating.ABSENT);
        }
        Feedback feedback = new Feedback(ratings);
        feedback.setInitialHint(wordToGuess);
        roundFeedback.add(feedback);
    }

    public Feedback doGuess(String guess){  //todo spatie/anderechars exceptie afdwingen
        if(guess.length() != wordToGuess.length()) throw new InvalidGuessLengthException();
        if(roundFeedback.size() == maxGuesses + 1) throw new ForbiddenGuessException("The game already has been lost.");
        if(getFeedback().isWordGuessed()) throw new ForbiddenGuessException("The word is already guessed.");

        Feedback generatedFeedback = new Feedback(guess, Feedback.generateRatings(guess, wordToGuess));
        generatedFeedback.setHint(roundFeedback.get(roundFeedback.size()-1).getHint(), wordToGuess);
        roundFeedback.add(generatedFeedback);
        return generatedFeedback;
    }

    public Feedback getFeedback(){
        return roundFeedback.get(roundFeedback.size()-1);
    }
}
