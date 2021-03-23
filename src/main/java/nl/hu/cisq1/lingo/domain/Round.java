package nl.hu.cisq1.lingo.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import nl.hu.cisq1.lingo.domain.Enums.GameState;
import nl.hu.cisq1.lingo.domain.Enums.Rating;
import nl.hu.cisq1.lingo.domain.Enums.RoundStatus;
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
    @Enumerated(EnumType.STRING)
    private RoundStatus roundOver;

    public Round(){}

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.roundOver = RoundStatus.ROUND_IS_RUNNING;
        List<Rating> ratings = new ArrayList<>();
        ratings.add(Rating.CORRECT);
        for(int i = 1; i <= wordToGuess.length(); i++){
            ratings.add(Rating.ABSENT);
        }
        Feedback feedback = new Feedback(ratings);
        feedback.setInitialHint(wordToGuess);
        roundFeedback.add(feedback);
    }

    public Feedback doGuess(String guess){
        if(roundOver != RoundStatus.ROUND_IS_RUNNING) throw new ForbiddenGuessException();//na nummer 6 wordt ie geblokt (5 guesses en 1 beginhint)
        //todo spatie/anderechars exceptie afdwingen
        if(guess.length() != wordToGuess.length()) throw new InvalidGuessLengthException();
        if(guess.equals(wordToGuess)) roundOver = RoundStatus.WORD_IS_GUESSED;
        if(roundFeedback.size() == 5 && roundOver != RoundStatus.WORD_IS_GUESSED) roundOver = RoundStatus.ROUND_IS_FAILED;
        //roundstatus wordt hier gezet bij de 6e guess (5e aanroep want 1e feedback staat er in door constructor)
        Feedback generatedFeedback = new Feedback(guess, Feedback.generateRatings(guess, wordToGuess));
        generatedFeedback.setHint(roundFeedback.get(roundFeedback.size()-1).getHint(), wordToGuess);
        roundFeedback.add(generatedFeedback);
        return generatedFeedback;
    }

    public Feedback getFeedback(){
        return roundFeedback.get(roundFeedback.size()-1);
    }
}
