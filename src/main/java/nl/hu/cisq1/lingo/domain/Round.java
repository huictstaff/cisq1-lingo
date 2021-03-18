package nl.hu.cisq1.lingo.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import lombok.NoArgsConstructor;
import nl.hu.cisq1.lingo.domain.exception.InvalidGuessLengthException;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenGuessException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Round {
    @Id
    @GeneratedValue
    private long id;
    private String wordToGuess;
    @OneToMany(mappedBy="round", cascade= CascadeType.ALL)
    private List<Feedback> roundFeedback = new ArrayList<>();
    private RoundStatus roundOver;
    @ManyToOne
    @JsonIgnore
    private Game game;

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.roundOver = RoundStatus.ROUND_IS_RUNNING;
        List<Rating> ratings = new ArrayList<>();
        ratings.add(Rating.CORRECT);
        for(int i = 1; i <= wordToGuess.length(); i++){
            ratings.add(Rating.ABSENT);
        }
        Feedback feedback = new Feedback(ratings);
        feedback.setRound(this);
        roundFeedback.add(feedback);
    }

    public void roundIsFailed(){
        roundOver = RoundStatus.ROUND_IS_FAILED;
        game.setGameState(GameState.ELIMINATED);
    }

    public Feedback doGuess(String guess){
        if(roundOver != RoundStatus.ROUND_IS_RUNNING) throw new ForbiddenGuessException();//na nummer 6 wordt ie geblokt (5 guesses en 1 beginhint)
        if(guess.length() != wordToGuess.length()) throw new InvalidGuessLengthException();
        if(guess.equals(wordToGuess)) roundOver = RoundStatus.WORD_IS_GUESSED;
        if(roundFeedback.size() == 5 && roundOver != RoundStatus.WORD_IS_GUESSED) roundIsFailed();
        //roundstatus wordt hier gezet bij de 6e guess (5e aanroep want 1e feedback staat er in door constructor)
        Feedback generatedFeedback = new Feedback(guess, Feedback.generateRatings(guess, wordToGuess));
        roundFeedback.add(generatedFeedback);
        return generatedFeedback;
    }

    public Feedback getFeedback(){
        return roundFeedback.get(roundFeedback.size()-1);
    }

}
