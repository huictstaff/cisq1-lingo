package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.domain.exception.InvalidFeedbackException;

import java.util.List;
import java.util.Objects;

public class Feedback {
    private String attempt;
    private List<Rating> ratings;

    public Feedback(String attempt, List<nl.hu.cisq1.lingo.words.domain.Rating> rating) {
        if (attempt.length() == rating.size()) {
            this.attempt = attempt;
            ratings = rating;
        } else {
            throw new InvalidFeedbackException(rating.size(), attempt.length());
        }

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
