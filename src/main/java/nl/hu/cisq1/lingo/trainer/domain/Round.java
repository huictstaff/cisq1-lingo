package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exeption.GameException;
import nl.hu.cisq1.lingo.trainer.domain.exeption.InvalidAttemptException;
import nl.hu.cisq1.lingo.trainer.domain.exeption.TooManyAttemptsException;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;

public class Round {

    private final Word word;
    private final List<Feedback> feedbackList = new ArrayList<>();

    public boolean isWordGuessed = false;

    public Round(Word word) {
        this.word = word;
    }

    /** make a guess, attempt */
    public void attempt(String guess) {
        /** TODO exceptions for round over, etc */

        Feedback newestFeedback = new Feedback();
        if (feedbackList.size() >= 5) {
            throw new TooManyAttemptsException("You have already used all attempts for this round");
        }
        /** check validity of attempt */
        if (attemptValid(guess)) {
            Feedback feedback = new Feedback().createFeedback(word.getValue(), guess);
            feedbackList.add(feedback);
            newestFeedback = feedback;
        } else {
            throw new InvalidAttemptException("the attempt is invalid");
        }

        if (newestFeedback.wordIsGuessed()) {
            isWordGuessed = true;
            throw new GameException("Guessed correctly, round is finished");
        }

        displayHint(newestFeedback);
    }

    private void displayHint(Feedback newestFeedback) {
        /** TODO future rework to fancier display? */
        System.out.println(newestFeedback.getMarks());
    }

    public boolean attemptValid(String attemptedWord) {
        /** TODO Length catch exception */
        if (!isWordLengthValid(attemptedWord)) {
            return false;
        }
        /** TODO Rework this abomination, what is this even supposed to achieve */
        if (!isWordGuessed(attemptedWord)) {
            return true;
        }
        return true;
    }

    /** TODO Maybe refactor this to Feedback responsibility */
    public boolean isWordLengthValid(String attemptedWord) {
        return this.word.getLength().equals(attemptedWord.length());
    }

    /** TODO Maybe refactor this to Feedback responsibility */
    public boolean isWordGuessed(String attemptedWord) {
        return attemptedWord.equals(this.word.getValue());
    }

    public List<Feedback> getFeedback() {
        return feedbackList;
    }


}
