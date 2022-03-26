package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;

public class Round {

    private final Word word;
    private final List<Feedback> feedback = new ArrayList<>();

    public Round(Word word) {
        this.word = word;
    }

    public boolean attempt(String attemptedWord) {
        /** TODO Length catch */
        if (!isWordLengthValid(attemptedWord)) {
            return false;
        }
        if (!isWordGuessed(attemptedWord)) {
            return false;
        }
        return true;
    }

    public boolean isWordLengthValid(String attemptedWord) {
        return this.word.getLength().equals(attemptedWord.length());
    }

    public boolean isWordGuessed(String attemptedWord) {
        return attemptedWord.equals(this.word.getValue());
    }
}
