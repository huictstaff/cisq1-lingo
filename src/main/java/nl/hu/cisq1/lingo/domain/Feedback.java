package nl.hu.cisq1.lingo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.hu.cisq1.lingo.data.HintConverter;
import nl.hu.cisq1.lingo.data.RatingsConverter;
import nl.hu.cisq1.lingo.domain.Enums.Rating;
import nl.hu.cisq1.lingo.domain.exception.InvalidFeedbackException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue
    private long id;
    @Convert(converter = HintConverter.class)
    private List<Character> hint;
    private String attempt;
    @Convert(converter = RatingsConverter.class)
    private List<Rating> ratings;

    public Feedback(String attempt, List<Rating> rating) {
        if (attempt.length() == rating.size()) {
            this.attempt = attempt;
            ratings = rating;
        } else {
            throw new InvalidFeedbackException(rating.size(), attempt.length());
        }
    }

    public Feedback(List<Rating> rating) {
        this.ratings = rating;
    }

    public static List<Rating> generateRatings(String attempt, String wordToGuess) {
        List<Rating> ratings = new ArrayList<>();

        if (attempt.length() != wordToGuess.length()) {
            for (int i = 0; attempt.length() > i; i++) {
                ratings.add(Rating.INVALID);
            }
            return ratings;
        }
        for (int i = 0; i < attempt.length(); i++) {
            if (attempt.charAt(i) == wordToGuess.charAt(i)) {
                ratings.add(Rating.CORRECT);
            } else {
                if (wordToGuess.contains(String.valueOf(attempt.charAt(i)))) {
                    ratings.add(Rating.PRESENT);
                } else {
                    ratings.add(Rating.ABSENT);
                }
            }
        }
        return ratings;
    }

    public boolean isWordGuessed() {
        return this.ratings.stream() //Stukje nu overgenomen van docenten, snap wat het doet.
                .allMatch(Rating.CORRECT::equals);
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
    public List<Character> setHint(List<Character> previousHint, String wordToGuess) {
        char[] guessWordList = wordToGuess.toCharArray();
        List<Character> newHint = new ArrayList<>();
        for (int iterator = 0; iterator < previousHint.size(); iterator++) {
            if ((previousHint.get(iterator) != '.')) {
                newHint.add(previousHint.get(iterator));
            } else if (ratings.get(iterator) == Rating.CORRECT) {
                newHint.add(guessWordList[iterator]);
            } else newHint.add('.');
        }
        this.hint = newHint;
        return newHint;
    }

    public void setInitialHint(String wordToGuess) {
        char[] guessWordList = wordToGuess.toCharArray();
        List<Character> newHint = new ArrayList<>();
        for (int iterator = 0; iterator < wordToGuess.length(); iterator++) {
            if (ratings.get(iterator) == Rating.CORRECT) {
                newHint.add(guessWordList[iterator]);
            } else newHint.add('.');
        }
        this.hint = newHint;
    }
}
