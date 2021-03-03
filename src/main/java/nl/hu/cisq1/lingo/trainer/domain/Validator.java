package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.LengthInvalidException;
import nl.hu.cisq1.lingo.words.domain.Word;

public class Validator {
    public Feedback validate(Word attempt, Word actualWord) {
        if(!isAttemptValid(attempt)) throw new LengthInvalidException();
        if(attempt.equals(actualWord)) return Feedback.correct(attempt.getValue());
    }

    private boolean isAttemptValid(Word attempt){
        return attempt.getLength() >= 5 && attempt.getLength() <= 7;
    }
}
