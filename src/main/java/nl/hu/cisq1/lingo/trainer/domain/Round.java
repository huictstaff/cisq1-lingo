package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.exception.IllegalMoveException;
import nl.hu.cisq1.lingo.trainer.exception.InvalidAttemptException;
import org.springframework.hateoas.Link;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Round implements Serializable {
    private int turns ;
    private String wordToGuess;
    private List<String> attempts;
    private List<Feedback> feedbackList;

    public Round (String wordToGuess){
        this.wordToGuess = wordToGuess;
        this.turns =0;
        this.attempts = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
//        List<Mark> feedback = new ArrayList<>();
//        for (int i = 0 ;i < wordToGuess.length();i++){
//            if (i == 0 )feedback.add(Mark.CORRECT);feedback.add(Mark.ABSENT);
//        }
    }

    public Feedback guessWord(String guess) {

        Feedback feedback = new Feedback(guess, Feedback.feedbackGenerator(guess, wordToGuess));
        if (guess.length() != wordToGuess.length())
            throw new InvalidAttemptException("word has to many letters or to few letters");
        if (turns <= 5 && !isRoundWon()) {
            turns++;
            attempts.add(guess);
            feedbackList.add(feedback);
            feedback.gaveHint();
            return feedback;
        }else throw new IllegalMoveException("Round is ended");
    }

    public boolean isRoundWon(){
        return feedbackList.get(feedbackList.size()-1).isWordGuessed();
    }
}
