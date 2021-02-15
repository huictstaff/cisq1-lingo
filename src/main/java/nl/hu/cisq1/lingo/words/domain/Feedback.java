package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.domain.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Feedback {
    private String attempt;
    private List<Rating> ratings;

    public Feedback(String attempt, List<Rating> rating) {
        if (attempt.length() == rating.size()) {
            this.attempt = attempt;
            ratings = rating;
        } else {
            throw new InvalidFeedbackException(rating.size(), attempt.length());
        }

    }

    public Feedback(List<Rating> rating){
        this.ratings = rating;
    }

    public boolean isWordGuessed() {
        for (Rating rating : this.ratings) {
            if (rating == Rating.ABSENT || rating == Rating.PRESENT) {
                return false;
            }
        }
        return true;
    }

    public boolean isWordInvalid() {
        for (Rating rating : this.ratings) {
            if (rating == Rating.INVALID) {
                return true;
            }
        }
        return false;
    }

    //Oude hint + this.marks + te raden woord = new hint
    public List<Character> giveHint(List<Character> previousHint, String wordToGuess) {
        char[] guessWordList = wordToGuess.toCharArray();
        List<Character> newHint = new ArrayList<>();
        for (int iterator = 0; iterator < previousHint.size(); iterator++) {
            if (!(previousHint.get(iterator) == '.')) {
                newHint.add(previousHint.get(iterator));
            } else if (ratings.get(iterator) == Rating.CORRECT) {
                newHint.add(guessWordList[iterator]);
            } else newHint.add('.');
        }
        return newHint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) && Objects.equals(ratings, feedback.ratings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, ratings);
    }
}
