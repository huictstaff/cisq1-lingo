package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.exeption.InvalidAttemptException;
import nl.hu.cisq1.lingo.trainer.domain.exeption.TooManyAttemptsException;
import nl.hu.cisq1.lingo.words.domain.Word;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Getter @Setter
    @OneToOne
    private Word word;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Feedback> feedbackList = new ArrayList<>();

    @Getter @Setter
    public boolean hasWordBeenGuessed = false;

    public Round(Word word) {
        this.word = word;
    }

    public Round() {}

    /** make a guess, attempt */
    public void attempt(String guess) {

        Feedback newestFeedback;
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
            hasWordBeenGuessed = true;
        }

        displayHint(newestFeedback);
    }

    private void displayHint(Feedback newestFeedback) {
        System.out.println(newestFeedback.getMarks());
    }

    public boolean attemptValid(String attemptedWord) {
        if (!isWordLengthValid(attemptedWord)) {
            return false;
        }

        if (isWordGuessed(attemptedWord)) {
            return true;
        }
        /** TODO another if with a check if the word has been used before */
        return true;
    }

    public boolean isWordLengthValid(String attemptedWord) {
        return this.word.getLength().equals(attemptedWord.length());
    }

    public boolean isWordGuessed(String attemptedWord) {
        return attemptedWord.equals(this.word.getValue());
    }

    public List<Feedback> getFeedback() {
        return feedbackList;
    }


}
