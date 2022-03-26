package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;

public class Round {

    private final Word word;
    private final List<Feedback> feedbackList = new ArrayList<>();

    public Round(Word word) {
        this.word = word;
    }

    public void attempt(String guess) {
        /** TODO exceptions for round over, etc */

        /** check validity of attempt */
        if (attemptValid(guess)) {
            Feedback feedback = new Feedback().createFeedback(word.getValue(), guess);
            feedbackList.add(feedback);
            System.out.println(feedback);
        }

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

    public boolean isWordLengthValid(String attemptedWord) {
        return this.word.getLength().equals(attemptedWord.length());
    }

    public boolean isWordGuessed(String attemptedWord) {
        System.out.println(attemptedWord);
        System.out.println(this.word.getValue());
        System.out.println(attemptedWord.equals(this.word.getValue()));
        return attemptedWord.equals(this.word.getValue());
    }

    public List<Feedback> getFeedback() {
        return feedbackList;
    }


}
