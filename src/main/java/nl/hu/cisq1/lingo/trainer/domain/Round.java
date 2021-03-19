package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.exception.IllegalMoveException;
import nl.hu.cisq1.lingo.trainer.exception.InvalidAttemptException;
import org.springframework.hateoas.Link;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Round implements Serializable {
    private String wordToGuess;
    private List<String> attempts;
    private List<Feedback> feedbackList;
    private RoundStatus roundStatus;

    public Round (String wordToGuess){
        this.wordToGuess = wordToGuess;
        this.attempts = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
        this.roundStatus = RoundStatus.Playing;
//        List<Mark> feedback = new ArrayList<>();
//        for (int i = 0 ;i < wordToGuess.length();i++){
//            if (i == 0 )feedback.add(Mark.CORRECT);feedback.add(Mark.ABSENT);
//        }
    }

    public Feedback guessWord(String guess) {

        if (guess.length() != wordToGuess.length())
            throw new InvalidAttemptException("word has to many letters or to few letters");
        else if (feedbackList.size() >= 5 || roundStatus != RoundStatus.Playing)
            throw new IllegalMoveException("Round is ended");

        Feedback feedback = new Feedback(guess, Feedback.feedbackGenerator(guess, wordToGuess));
        attempts.add(guess);
        feedback.gaveHint();
        feedbackList.add(feedback);
        if (isRoundWon()) {
            this.roundStatus = RoundStatus.Win;
        } else if (feedbackList.size() == 5 && !isRoundWon()) {
            this.roundStatus = RoundStatus.Lose;
        }
        return feedback;
    }


    public Feedback firstHint(){
        List<Mark> marks = new ArrayList<>();
        for (int i =0 ; i < this.wordToGuess.length(); i++) {
            if (i == 0) marks.add(Mark.CORRECT);
            else marks.add(Mark.ABSENT);
        }

        String attempt =  wordToGuess.charAt(0) + ".".repeat(wordToGuess.length() - 1);
        Feedback feedback = new Feedback(attempt,marks);
        feedback.gaveHint();
        return feedback;
    }

    public boolean isRoundWon(){
        return feedbackList.get(feedbackList.size()-1).isWordGuessed();
    }

    public RoundStatus getRoundStatus ( ) {
        return roundStatus;
    }

    public List<Feedback> getFeedbackList ( ) {
        return feedbackList;
    }

    public String getWordToGuess ( ) {
        return wordToGuess;
    }
}
