package nl.hu.cisq1.lingo.trainer.domain;

import lombok.RequiredArgsConstructor;
import nl.hu.cisq1.lingo.words.domain.Word;

@RequiredArgsConstructor
public class Turn {
    private final Validator validator;
    private final Word guess;
    private final Word actualWord;

    public Feedback turn(){
        return validator.validate(guess, actualWord);}
}
