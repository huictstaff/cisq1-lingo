package nl.hu.cisq1.lingo.domain;
import lombok.Getter;
import nl.hu.cisq1.lingo.domain.exception.InvalidGuessLengthException;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenGuessException;

import java.util.ArrayList;
import java.util.List;
public class Round {
    private String wordToGuess;
    private List<Feedback> roundFeedback = new ArrayList<>();
    @Getter
    private RoundStatus roundOver;

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.roundOver = RoundStatus.ROUND_IS_RUNNING;
        List<Rating> ratings = new ArrayList<>();
        ratings.add(Rating.CORRECT);
        for(int i = 1; i <= wordToGuess.length(); i++){ //Nakijken voor pittest
            ratings.add(Rating.ABSENT);
        }
        roundFeedback.add(new Feedback(ratings));
    }

    public Feedback doGuess(String guess){
        if(roundOver != RoundStatus.ROUND_IS_RUNNING) throw new ForbiddenGuessException();//na nummer 6 wordt ie geblokt (5 guesses en 1 beginhint)
        if(guess.length() != wordToGuess.length()) throw new InvalidGuessLengthException();
        if(guess.equals(wordToGuess)) roundOver = RoundStatus.WORD_IS_GUESSED;
        if(roundFeedback.size() == 5 && roundOver != RoundStatus.WORD_IS_GUESSED) roundOver = RoundStatus.ROUND_IS_FAILED;
        //roundstatus wordt hier gezet bij de 6e guess (5e aanroep want 1e feedback staat er in door constructor)
        Feedback generatedFeedback = new Feedback(guess, Feedback.generateRatings(guess, wordToGuess));
        roundFeedback.add(generatedFeedback);
        return generatedFeedback;
    }

    public List<Feedback> getFeedbacks(){
        return roundFeedback;
    }
}
